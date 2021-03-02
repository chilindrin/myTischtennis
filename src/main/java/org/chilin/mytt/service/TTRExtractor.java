package org.chilin.mytt.service;

import org.springframework.stereotype.Service;

@Service
public class TTRExtractor {

    private static final String TTR_BIG_PART_LOCATOR = "class=\"ttr\"";
    private static final String TTR_FRAGMENT_LOCATOR = "Historie\">TTR";
    private static final int DISTANCE_TO_TTR_POSITION = 14;
    private static final int FRAGMENT_LENGTH = 70;
    private static final int LENGTH_TTR_NUMBER = 4;

    public String extractTTRPoints(String wholeMyTischtennisPageWithTTRPoints) {
        int positionOfMyTtrPoints = wholeMyTischtennisPageWithTTRPoints.indexOf(TTR_BIG_PART_LOCATOR);
        String fragmentWithTtrPoints = wholeMyTischtennisPageWithTTRPoints.substring(positionOfMyTtrPoints,positionOfMyTtrPoints+FRAGMENT_LENGTH);
        int searchStringPosition = fragmentWithTtrPoints.indexOf(TTR_FRAGMENT_LOCATOR);
        int ttrPointsPosition = searchStringPosition + DISTANCE_TO_TTR_POSITION;
        return fragmentWithTtrPoints.substring(ttrPointsPosition, ttrPointsPosition + LENGTH_TTR_NUMBER);
    }

}
