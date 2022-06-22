public class Time2Enhanced{
    private final int SECONDS_IN_MINUTE = 60;
    private final int MINUTES_IN_HOUR   = 60;
    private final int SECONDS_IN_HOUR   = 3600;
    private final int HOURS_IN_DAY      = 24;

    private int secondsSinceMidnight;

    // Time2Enhanced�޲������캯��:
    // ��ÿ��ʵ��������ʼ��Ϊ��
    public Time2Enhanced(){
        this(0, 0, 0);  //ʹ�������������� Time2Enhanced ���캯��
    }
    // Time2Enhanced���캯�����ṩСʱ�����Ӻ���Ĭ��Ϊ 0
    public Time2Enhanced(int h){
        this(h, 0, 0);
    }
    // Time2Enhanced ���캯�����ṩСʱ�ͷ��ӣ���Ĭ��Ϊ 0
    public Time2Enhanced(int h, int m){
        this(h, m, 0);
    }
    // Time2Enhanced ���캯�����ṩСʱ�����Ӻ���
    public Time2Enhanced(int h, int m, int s){
        setTime(h, m, s);
    }
    // Time2Enhanced ���캯�����ṩ��һ�� Time2Enhanced ����
    public Time2Enhanced(Time2Enhanced time){
    	// ���� Time2Enhanced ���������캯��
        this(time.getHour(), time.getMinute(), time.getSecond());
    }
    
    // SETTERS
    // ʹ��ͨ��ʱ�������µ�ʱ��ֵ
    // ��֤����
    public void setTime(int h, int m, int s){
        setHour(h);
        setMinute(m);
        setSecond(s);
    }
    // ��֤������ hour
    public void setHour(int h){
        if(h >= 0 && h < HOURS_IN_DAY)
            secondsSinceMidnight += h * SECONDS_IN_HOUR;
        else
            throw new IllegalArgumentException("hour must be 0-23");
    }
    // ��֤������ minute
    public void setMinute(int m){
        if(m >= 0 && m < MINUTES_IN_HOUR)
            secondsSinceMidnight += m * SECONDS_IN_MINUTE;
        else
            throw new IllegalArgumentException("minute must be 0-59");
    }
    // ��֤������ second
    public void setSecond(int s){
        if(s >= 0 && s < SECONDS_IN_MINUTE)
            secondsSinceMidnight += s;
        else
            throw new IllegalArgumentException("second must be 0-59");
    }
    
    // ���ӷ���
    public void incrementMinute(){
        // ȷ��û�����
        if(getMinute() < MINUTES_IN_HOUR - 1)
            secondsSinceMidnight += SECONDS_IN_MINUTE;
        // if so reset minutes to zero and increment hour
        else{
            secondsSinceMidnight -= (MINUTES_IN_HOUR - 1) * SECONDS_IN_MINUTE;
            incrementHour();
        }
    }
    // ����Сʱ
    public void incrementHour(){
        // ȷ��û�����
        if(getHour() < HOURS_IN_DAY - 1)
            secondsSinceMidnight += SECONDS_IN_HOUR;
        // if so reset hours to zero
        else
            secondsSinceMidnight -= (HOURS_IN_DAY - 1) * SECONDS_IN_HOUR;
    }
    
    // GETTERS
    // get hour
    public int getHour(){
        return secondsSinceMidnight / SECONDS_IN_HOUR;
    }
    // get minute
    public int getMinute(){
        return (secondsSinceMidnight % SECONDS_IN_HOUR) / MINUTES_IN_HOUR;
    }
    // get second
    public int getSecond(){
        return secondsSinceMidnight % SECONDS_IN_MINUTE;
    }

    // ת��Ϊͨ��ʱ���ʽ���ַ��� (HH:MM:SS)
    public String toUniversalString(){
        return String.format(
                "%02d:%02d:%02d", getHour(), getMinute(), getSecond());
    }
    // ת��Ϊ��׼ʱ���ʽ���ַ��� (H:MM:MM AM/PM)
    public String toString(){
    	//hour��0����Ϊ12
        return String.format("%d:%02d:%02d %s",
                ((getHour() == 0 || getHour() == 12) ? 12 : getHour() % 12),
                getMinute(), getSecond(), (getHour() < 12 ? "AM" : "PM"));
    }
}