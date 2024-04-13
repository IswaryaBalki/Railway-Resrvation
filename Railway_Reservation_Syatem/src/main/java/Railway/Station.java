package Railway;

public class Station
{
    private static Station instance;
	private String name;
    private String from;
    private String to;
    private String code;
    //private boolean stop;
    Station(){};
    Station(String name)
    {
        this.name=name;
          
    }
    Station(String from,String to)
    {
    	this.from=from;
    	this.to=to;
    	
    }
    public String getStationName()
    {
        return name;
    }
    public String getStationCode()
    {
        return code;
    }
    public String getFrom()
    {
    	return from;
    }
    public String getTo()
    {
    	return to;
    }
	public static Station getInstance() {
		if(instance==null)
			instance=new Station();
		return instance;
	}

}
