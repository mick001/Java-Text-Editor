//------------------------------------------------------------------------------
// Name:        Basic text editor written in Java
// Purpose:     
//
// Created:     12/10/2014
// Copyright:   (c) Copyright Michy 2014
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

// A class to save data to a .txt file

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class SaveData
{
	// Class variables
	private String filepath;		// Filepath
	
	// Constructor
	public SaveData(String filepath)
	{
		this.filepath = filepath;
	}
	
	// Save the string s into the specified path
	public void save(String s)
	{
		try
		{
			PrintStream output = new PrintStream(this.filepath);
			output.println(s);
			output.close();
		}catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
