class TimeUtil {
    private static long iniTime = System.currentTimeMillis();
    private static long hourDuration = 2000;
    static long getCurrentHour(){
        long Time = (System.currentTimeMillis() - iniTime) / hourDuration;
        if (Time > 23){
            iniTime = System.currentTimeMillis();
        }
        return Time;
    }
    static long calcMillisTillNextHour(){
        return hourDuration - (System.currentTimeMillis() - iniTime) % hourDuration;
    }
}