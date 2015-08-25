//------------------------------------------------------------------------------
// Name:        Basic text editor written in Java
// Purpose:     
//
// Created:     12/10/2014
// Copyright:   (c) Copyright Mic 2014
// Licence:     GNU GPL
/*
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

// ReadData class: a class used to load the data from any .txt file directly onto the UI editor.

import java.util.Scanner;
import java.io.*;

public class ReadData 
{
	// Class variables
	Scanner scan;				// Scanner objecy
	static String data;			// Data read from file
	private String filepath;	// Filepath
	
	// Constructor
	public ReadData(String s)
	{
		this.filepath = s;
	}
	
	// Open file method
	public void open()
	{
		try
		{
			scan = new Scanner(new File(this.filepath));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	// Read file content and store it into the data variable
	public void read()
	{
		do
		{
			data = scan.next();
		}while(scan.hasNext());
		scan.close();
	}
}
