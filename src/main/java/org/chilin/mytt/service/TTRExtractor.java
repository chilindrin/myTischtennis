package org.chilin.mytt.service;

import org.chilin.common.vo.TTRHistoryVO;
import org.springframework.stereotype.Service;

@Service
public class TTRExtractor {

    private static final String TTR_BIG_PART_LOCATOR = "class=\"ttr\"";
    private static final String TTR_FRAGMENT_LOCATOR = "Historie\">TTR";
    private static final String QTTR_FRAGMENT_LOCATOR = "Historie\">Q-TTR";
    private static final int DISTANCE_TO_TTR_POSITION = 14;
    private static final int DISTANCE_TO_QTTR_POSITION = 16;
    private static final int FRAGMENT_LENGTH = 160;
    private static final int LENGTH_TTR_NUMBER = 4;

    public TTRHistoryVO createTTREntry(String wholeMyTischtennisPageWithTTRPoints) {
        int positionOfMyTtrPoints = wholeMyTischtennisPageWithTTRPoints.indexOf(TTR_BIG_PART_LOCATOR);
        String fragmentWithTtrAndQttrPoints = wholeMyTischtennisPageWithTTRPoints.substring(positionOfMyTtrPoints,positionOfMyTtrPoints+FRAGMENT_LENGTH);

        TTRHistoryVO result = new TTRHistoryVO();
        result.setTtr(getTtrPoints(fragmentWithTtrAndQttrPoints));
        result.setQttr(getQttrPoints(fragmentWithTtrAndQttrPoints));

        return result;
    }

    private Integer getTtrPoints(String fragmentWithTtrPoints){
        int searchTTRStringPosition = fragmentWithTtrPoints.indexOf(TTR_FRAGMENT_LOCATOR);
        int ttrPointsPosition = searchTTRStringPosition + DISTANCE_TO_TTR_POSITION;
        String ttrPoints = fragmentWithTtrPoints.substring(ttrPointsPosition, ttrPointsPosition + LENGTH_TTR_NUMBER);
        return Integer.valueOf(ttrPoints);
    }

    private Integer getQttrPoints(String fragmentWithQttrPoints){
        int searchQTTRStringPosition = fragmentWithQttrPoints.indexOf(QTTR_FRAGMENT_LOCATOR);
        int qttrPointsPosition = searchQTTRStringPosition + DISTANCE_TO_QTTR_POSITION;
        String qttrPoints = fragmentWithQttrPoints.substring(qttrPointsPosition , qttrPointsPosition + LENGTH_TTR_NUMBER);
        return Integer.valueOf(qttrPoints);
    }

}
