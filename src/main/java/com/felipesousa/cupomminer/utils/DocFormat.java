package com.felipesousa.cupomminer.utils;

public class DocFormat {

    public static String formatDOc(Long doc) {
        String docStr = doc.toString();
        int length = docStr.length();
        StringBuilder formatted = new StringBuilder();

        int[] positions = {3, 6, 9};

        int positionIndex = positions.length - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (positionIndex >= 0 && (length - i) == positions[positionIndex]) {
                formatted.insert(0, '.');
                positionIndex--;
            }
            formatted.insert(0, docStr.charAt(i));
        }

        if (length > 11) {
            formatted.insert(formatted.length() - 2, '.');
        }

        return formatted.toString();
    }
}
