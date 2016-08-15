package payment;

import java.io.BufferedReader;
import java.util.Scanner;

public class MainClass {
	
//	public static void main(String[] args) {
//		
//		System.out.println("Enter no. of members, distance in miles, CarType");
//		
//		Scanner scan = new Scanner(System.in);
//		int no_members = scan.nextInt();
//		double dist  = scan.nextDouble();
//		double park_hrs;
//		String carType = scan.next();
//		String parkType;
//		
//		Payment pay1, pay2;
//		CalculatePayment Typ1, Typ2, Typ3;
//		CardPayment c1;
//		OnlinePayment op1;
//		Parking prk;
//		
//		
//		
//		if (carType.equals("Type1"))
//		{
//			
//			Typ1 = new EconomyCar(dist,no_members);
//			Typ1.cost_for_distance();
//			System.out.println("Specify Parking Type\n1.P1\n2.P2\n3.P3");
//			parkType = scan.next();
//			System.out.println("Specify Parking Hours?");
//			park_hrs  = scan.nextDouble();
//			switch(parkType)
//			{
//			case "P1":
//			case "1":
//				OpenParking p1 = new OpenParking(Typ1,no_members);
//				p1.addCharges(park_hrs);
//				break;
//				
//			case "P2":
//			case "2":
//				ClosedParking p2 = new ClosedParking(Typ1,no_members);
//				p2.addCharges(park_hrs);
//				break;				
//				
//			default: 
//				System.out.println("\nIncorrect Option. Payment not made");
//				break;			
//			}
//			
//			System.out.println("Choose Payment options:\n1.Card \n2.Online Payment");
//			String pay_type = scan.next();
//			if(pay_type.equals("1") || pay_type.equals("Card") )
//			{
//				c1 = new CardPayment(Typ1, no_members);
//				c1.make_payment();
//				
//			}
//			else if (pay_type.equals("2") || pay_type.equals("Online Payment"))
//			{
//				op1 = new OnlinePayment(Typ1, no_members);
//				op1.make_payment();
//			}
//			else
//			{
//				System.out.println("\nIncorrect Option");
//			}
//			
//			
//		}
//		
//		else if (carType.equals("Type2"))
//		{
//			Typ2 = new LuxuryCar(dist,no_members);
//			Typ2.cost_for_distance();
//			System.out.println("Specify Parking Type\n1.P1\n2.P2");
//			parkType = scan.next();
//			System.out.println("Specify Parking Hours?");
//			park_hrs  = scan.nextDouble();
//			switch(parkType)
//			{
//			case "P1":
//			case "1":
//				OpenParking p1 = new OpenParking(Typ2,no_members);
//				p1.addCharges(park_hrs);
//				break;
//				
//			case "P2":
//			case "2":
//				ClosedParking p2 = new ClosedParking(Typ2,no_members);
//				p2.addCharges(park_hrs);
//				break;	
//				
//				
//			default: 
//				System.out.println("\nIncorrect Option. Payment not made");
//				break;			
//			}
//			
//			System.out.println("Choose Payment options:\n1.Card \n2.Online Payment");
//			String pay_type = scan.next();
//			if(pay_type.equals("1") || pay_type.equals("Card") )
//			{
//				c1 = new CardPayment(Typ2, no_members);
//				c1.make_payment();
//				
//			}
//			else if (pay_type.equals("2") || pay_type.equals("Online Payment"))
//			{
//				op1 = new OnlinePayment(Typ2, no_members);
//				op1.make_payment();
//			}
//			else
//			{
//				System.out.println("\nIncorrect Option");
//			}	
//		}
//		
//		else
//		{
//			System.out.println("\nIncorrect Car Type");
//		}
//		
//	
//	}
	
}
