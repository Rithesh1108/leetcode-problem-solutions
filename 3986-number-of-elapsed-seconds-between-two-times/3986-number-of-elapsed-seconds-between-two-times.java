class Solution {
    public int secondsBetweenTimes(String startTime, String endTime) {
        String[] st=startTime.split(":");
        String[] end=endTime.split(":");
        int stSec=Integer.parseInt(st[0])*3600 +Integer.parseInt(st[1])*60 +Integer.parseInt(st[2]);
        int endSec=Integer.parseInt(end[0])*3600 +Integer.parseInt(end[1])*60 +Integer.parseInt(end[2]);
        return endSec-stSec;
    }
}