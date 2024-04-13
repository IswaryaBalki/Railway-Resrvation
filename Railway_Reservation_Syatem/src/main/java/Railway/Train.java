package Railway;

import java.util.*;
import java.time.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class Train
{
    private int trainNum;
    private String status;
    private String name;
    private LinkedHashMap<Integer,Train> trains=new LinkedHashMap<Integer,Train>();  
    private Station stations;
    private Coach coach;
    private Schedule schedules;
    private LinkedList<Coach> coaches;
    private static Train train=null;
    public LocalDate date;
    Train(){}
    
    
    Train(int trainNum,String name,Schedule schedules,Coach coach)
    {
        this.trainNum = trainNum;
        this.name = name;
        this.coach=coach;
        this.schedules=schedules;
       
    }
 
    public Train(int trainNum, String name) {
    	this.trainNum = trainNum;
        this.name = name;
	}


	public Train(int trainNum, String name, Station stations, Schedule schedules, LinkedList<Coach> coaches)
	{
		this.trainNum = trainNum;
        this.name = name;
        this.coaches=coaches;
        this.schedules=schedules;
        this.stations=stations;
		
	}


	public String toString()
    {
        return "Train{" +
            "trainNo=" + trainNum +
            ", status='" + status + '\'' +
            ", name='" + name + '\'' +
           
            '}';
    }
    public static Train getTrainInstance()
    {
        if(train==null)
        {
           train=new Train();
        }
        return train;
    }
//    public  Train createTrainInstance(int trainNo, String name, String status, HashMap<Integer, LinkedHashSet<Station>> stations, HashMap<Integer, LinkedHashSet<Schedule>> schedules, HashMap<Integer, HashMap<String, Seat>> seats) {
//         Train newTrain = new Train(trainNo, name, status, stations, schedules, seats);
//         train.createNewTrainInstance(newTrain);
//         return newTrain;
//     }

    public String getTrainName()
    { 
        return name;
    }
    public int getTrainNum()
    {
        return trainNum;
    }
    public String getTrainStatus()
    {
        return status;
    }
    public LinkedHashMap<Integer,Train> getTrains()
    {
        return trains;
    }
    public  LinkedList<Coach> getCoaches() {
		return coaches;
	}

	public Schedule getSchedule() {
		return schedules;
	}
	public Station getStation() {
		return stations;
	}
	public  Coach getCoach() {
		return coach;
	}




}
