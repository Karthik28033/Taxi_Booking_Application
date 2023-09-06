package com.Taxi_Booking_Application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TaxiBooking {
	public static void BookTaxi(int CustomerId,char Pickupspot,char Dropspot,int Pickuptime,List<TaxiInfo>FreeTaxis) {
		//To Find Nearest
		int min=999;
		
		//Distance Between Pickup spot And Drop Spot
		int DistanceBetweenPickupspotAndDropspot=0;
		
		//Trip Earning
		int Earning=0;
		
		//When Taxi Will Be Free Next
		int NextFreeTime=0;
		
		//Where Taxi's After Trip Is Over
		char NextSpot ='Z';
		
		//Booked Taxi
		TaxiInfo BookedTaxi=null;
		
		//All Details Of Current Trip As String
		String TripDetail=" ";
		
		for (TaxiInfo t:FreeTaxis) {
			int DistanceBetweenCustomerAndTaxi =Math.abs((t.CurrentSpot - '0')-(Pickupspot - '0')) * 15;
			if(DistanceBetweenCustomerAndTaxi<min) {
				BookedTaxi = t;
				
				//Distance Between Pickup spot And Drop spot = (Drop spot - Pickup spot) * 15Km
				DistanceBetweenPickupspotAndDropspot = Math.abs((Dropspot - '0')-(Pickupspot - '0')) * 15;
				
				//Trip Earning = 100 + (DistanceBetweenPickupspotAndDropspot-5)*10
				Earning=(DistanceBetweenPickupspotAndDropspot-5)*10+100;
				
				//Drop Time Calculation
				int DropTime=Pickuptime+DistanceBetweenPickupspotAndDropspot/15;
				
				//When Taxi Will Be Free Next
				NextFreeTime = DropTime;
				
				//Taxi Will Be At Drop Spot After Trip
				NextSpot = Dropspot;
				
				 //Creating Trip Detail
				TripDetail =  "		   " + CustomerId + "		" + Pickupspot + "	 " + Dropspot + "	    " + Pickuptime + "	        " + DropTime + "	   " + Earning;
				
				//Setting Corresponding Details To Allotted Taxi
				BookedTaxi.setDetails(true, NextSpot, NextFreeTime, Earning, TripDetail);
				//Booked Successfully
				System.out.println("TAXI " + BookedTaxi.a + "BOOKED");
			}
		}
	}
	
	public static List<TaxiInfo> TotalTaxis(int n){
		List<TaxiInfo>Taxis = new ArrayList<TaxiInfo>();
		
		for(int i=1;i<=n;i++) {
			TaxiInfo t=new TaxiInfo();
			Taxis.add(t);
		}
		return Taxis;
		
	}
	public static List<TaxiInfo>getFreeTaxis(List<TaxiInfo> Taxis,int Pickuptime,char Pickupspot){
		List<TaxiInfo> FreeTaxis = new ArrayList<TaxiInfo>();
		for(TaxiInfo t:Taxis) {
			//Taxi Should Be Free
			//Taxi Should have Enough Time To Reach Customer Before Pickup time
			if(t.FreeTime<=Pickuptime && (Math.abs((t.CurrentSpot - '0')-(Pickupspot - '0')) <=Pickuptime - t.FreeTime));
			FreeTaxis.add(t);
		}
		return FreeTaxis;
	}

	public static void main(String[] args) {
		List<TaxiInfo>Taxis = TotalTaxis(4);
		Scanner sc = new Scanner(System.in);
		int a=1;
		
		while(true) {
			System.out.println("Press 0 to Book Taxi");
			System.out.println("Press 1 to know Taxi details");
			int choice = sc.nextInt();
			
			switch(choice) {
			case 0:{
				//Get Details From Customer
				int CustomerID = a;
				System.out.println("ENTER PICKUP SPOT");
				char Pickupspot =sc.next().charAt(0);
				System.out.println("ENTER DROP SPOT");
				char Dropspot = sc.next().charAt(0);
				System.out.println("ENTER PICKUP TIME");
				int PickupTime = sc.nextInt();
				
				//Check If Pickup And Drop Spot Are Valid
				if(Pickupspot<'A' || Dropspot>'F' || Pickupspot>'F' || Dropspot<'A') {
					System.out.println("VALID PICKUP SPOT AND DROP SPOT ARE A,B,C,D,E,F. EXITTING");
					return;
				}
				//Get All Free Taxis That Can Reach Customer On Or Before Pickup Time
				List<TaxiInfo> FreeTaxis = getFreeTaxis(Taxis,PickupTime,Pickupspot);
				
				//No Free Taxi Means We Cannot Allot,Exit!
				if(FreeTaxis.size()==0) {
					System.out.println("NO TAXI CAN BE ALLOTED. EXITTING");
					return;
				}
				//Sort Taxis Based On Earning
				Collections.sort(FreeTaxis,(x,y)->x.TotalEarning - y.TotalEarning);
				
				//Get Available Taxi Nearest To Us
				BookTaxi(a,Pickupspot,Dropspot,PickupTime,FreeTaxis);
				a++;
				break;
			}
			case 1:
			{
				for(TaxiInfo t: Taxis) {
					t.printTaxiInfo();
				}
				for(TaxiInfo t: Taxis) {
					t.printInfo();
				}
				break;
			}
			default:
				return;
				
			}
		}
	}

}
