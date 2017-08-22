package org.olivierpt.appservice.api.utils;

public class Chrono {

    private long startTime = 0;
    private long endTime = 0;
    private long startPause = 0;
    private long endPause = 0;
    private long duration = 0;

    public void start() {
        startTime = System.currentTimeMillis();
        endTime = 0;
        startPause = 0;
        endPause = 0;
        duration = 0;
    }

    public void pause() {
        if (startTime == 0) {
            return;
        }
        startPause = System.currentTimeMillis();
    }

    public void resume() {
        if (startTime == 0) {
            return;
        }
        if (startPause == 0) {
            return;
        }
        endPause = System.currentTimeMillis();
        startTime = startTime + endPause - startPause;
        endTime = 0;
        startPause = 0;
        endPause = 0;
        duration = 0;
    }

    public void stop() {
        if (startTime == 0) {
            return;
        }
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) - (endPause - startPause);
        startTime = 0;
        endTime = 0;
        startPause = 0;
        endPause = 0;
    }

    public long getDurationSec() {
        return duration / 1000;
    }

    public long getDurationMs() {
        return duration;
    }

    public String getDurationTxt() {
        return timeToHMS(getDurationSec());
    }

    public static String timeToHMS(long tempsS) {

        // IN : (long) temps en secondes
        // OUT : (String) temps au format texte : "1 h 26 min 3 s"

        int h = (int) (tempsS / 3600);
        int m = (int) ((tempsS % 3600) / 60);
        int s = (int) (tempsS % 60);

        String r = "";

        if (h > 0) {
            r += h + " h ";
        }
        if (m > 0) {
            r += m + " min ";
        }
        if (s > 0) {
            r += s + " s";
        }
        if (h <= 0 && m <= 0 && s <= 0) {
            r = "0 s";
        }

        return r;
    }

}
