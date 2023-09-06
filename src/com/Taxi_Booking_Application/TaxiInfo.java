package com.Taxi_Booking_Application;

import java.util.ArrayList;
import java.util.List;

public class TaxiInfo {
	static int Taxicount = 0;  //Number of Taxi
	int a;              
	boolean Booked;            //Taxi Booked Or Not
	char CurrentSpot;          //Taxi's Current Location Or Current Spot
	int FreeTime;              //When Taxi Is Available
	int TotalEarning;          //Total Earning Of Taxi
	List<String>Trips;         //Details About Trip
	
	public TaxiInfo(){
		Booked=true;
		CurrentSpot='A';
		FreeTime=5;            //5AM
		TotalEarning=0;
		Trips=new ArrayList<String>();
		Taxicount=Taxicount+1;
		a=Taxicount;
	}
	public void setDetails(boolean Booked,char CurrentSpot,int FreeTime,int TotalEarning,String TripInfo ) {
		this.Booked = Booked;           
		this.CurrentSpot = CurrentSpot;         
		this.FreeTime = FreeTime;              
		this.TotalEarning = TotalEarning;
		this.Trips.add(TripInfo);
	}
	public void printInfo(){
		//Print TripInfo
		System.out.println(" ");
		System.out.println("TAXI - " + this.a + " TOTAL EARNING - " + this.TotalEarning);
		System.out.println("TAXI ID    BOOKING ID    CUSTOMER ID    FROM    TO    PICKUPTIME    DROPTIME    AMOUNT");
		for(String Trip:Trips) {
			System.out.println(a + "          " + Trip);
		}
		System.out.println("---------------------------------------------------------------------------------------");
	}
	public void printTaxiInfo() {
		//print current location, free time and total earning
		System.out.println("TAXI - " + this.a + " ==> TOTAL EARNINGS - " + this.TotalEarning + " | CURRENTSPOT - " + this.CurrentSpot + " | FREE TIME - " + this.FreeTime);
	}
}
