/**
 *	rscplus
 *
 *	This file is part of rscplus.
 *
 *	rscplus is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	rscplus is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with rscplus.  If not, see <http://www.gnu.org/licenses/>.
 *
 *	Authors: see <https://github.com/OrN/rscplus>
 */

package Game;

import Client.Launcher;
import Client.Logger;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection
{
	public static void Load()
	{
		try
		{
			// Client
			Class<?> c = Launcher.instance.classLoader.loadClass("client");
			Method[] methods = c.getDeclaredMethods();
			for(Method method : methods)
			{
				if(method.toGenericString().equals(DISPLAYMESSAGE))
				{
					displayMessage = method;
					Logger.Info("Found displayMessage");
				}
			}

			// Camera
			c = Launcher.instance.classLoader.loadClass("lb");
			methods = c.getDeclaredMethods();
			for(Method method : methods)
			{
				if(method.toGenericString().equals(SETCAMERASIZE))
				{
					setCameraSize = method;
					Logger.Info("Found setCameraSize");
				}
			}

			// Renderer
			c = Launcher.instance.classLoader.loadClass("ua");
			methods = c.getDeclaredMethods();
			for(Method method : methods)
			{
				if(method.toGenericString().equals(SETGAMEBOUNDS))
				{
					setGameBounds = method;
					Logger.Info("Found setGameBounds");
				}
			}

			// Menu
			c = Launcher.instance.classLoader.loadClass("qa");
			menuX = c.getDeclaredField("kb");
			menuY = c.getDeclaredField("B");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static Field menuX = null;
	public static Field menuY = null;

	public static Method displayMessage = null;
	public static Method setCameraSize = null;
	public static Method setGameBounds = null;

	// Method descriptions
	private static final String DISPLAYMESSAGE = "private final void client.a(boolean,java.lang.String,int,java.lang.String,int,int,java.lang.String,java.lang.String)";
	private static final String SETCAMERASIZE = "final void lb.a(int,boolean,int,int,int,int,int)";
	private static final String SETGAMEBOUNDS = "final void ua.a(int,int,int,int,byte)";
}