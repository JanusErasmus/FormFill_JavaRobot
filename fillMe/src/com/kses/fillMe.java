package com.kses;

import java.awt.Robot;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

class fillMe
{
	/**
	 * Open and read a file, and return the lines in the file as a list
	 * of Strings.
	 * (Demonstrates Java FileReader, BufferedReader, and Java5.)
	 */
	public static ArrayList<String> readFile(String filename)
	{

		System.out.println("Opening " + filename);

		ArrayList<String> records = new ArrayList<String>();
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null)
			{
				records.add(line);
			}
			reader.close();
			return records;
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
			return null;
		}
	}

	public static void typeNumber(Robot robot, String numberString)
	{
		byte[] number = numberString.getBytes(Charset.forName("UTF-8"));

		if(number != null)
		{
			for (byte b : number) 
			{
				int code = b;
				// keycode only handles [A-Z] (which is ASCII decimal [65-90])
				if (code > 96 && code < 123)
					code = code - 32;
				
				robot.keyPress(code);
				robot.keyRelease(code);

				robot.delay(40);			
			}

			robot.keyPress('\t');
			robot.keyRelease('\t');
			robot.delay(200);
			robot.keyPress('\t');
			robot.keyRelease('\t');
			robot.delay(40);
		}
	}
	
	public static void main(String args[])
	{

		if(args.length < 1)
			return;

		try
		{
			Robot robot = new Robot();
			ArrayList<String> list = readFile(args[0]);

			System.out.println("Click on the website so I can fill it");
			robot.delay(5000);
			
			for(String line : list)
			{
				typeNumber(robot, line);
			}
			
			
			
			

		}
		catch(Exception e)
		{
			System.out.println("Bad Robot " + e);
			return;
		}

	}
}
