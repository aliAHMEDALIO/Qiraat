					/* In the name of GOD, the Most Gracious, the Most Merciful */
package org.qiraat;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
 
public class SurahDataParser
{
	public static ArrayList<String> getAyahList(Context context,XmlPullParser xpp,int position)
	{
		ArrayList<String> ayahList = new ArrayList<String>();
		try 
		{
			//XmlPullParser xpp=context.getResources().getXml(R.xml.qurandata);
			boolean suraFound=false;
			boolean suraFoundandFinishedGet = false;
			while ((xpp.getEventType()!=XmlPullParser.END_DOCUMENT))
			{
				if (xpp.getEventType()==XmlPullParser.START_TAG)
				{
					if(xpp.getName().equals("sura"))
					{
						if(xpp.getAttributeValue(0).equals(position+""))
						{
							xpp.nextTag();
							suraFound = true;
							suraFoundandFinishedGet=false;
							continue;
						}
						else
						{
							if (suraFound)
							{
								suraFoundandFinishedGet = true;
							}
						}
					}
					if(suraFoundandFinishedGet)
					{
						break;
					}
					if (xpp.getName().equals("aya") && suraFound)
					{
						
						String ayah=ArabicUtilities.reshapeSentence(xpp.getAttributeValue(1));
						ayahList.add(ayah);
						
						/*String aya=xpp.getAttributeValue(1).toString();
						String finalAya = aya.substring(0,1) + aya.substring(1,aya.length()-1) + aya.substring(aya.length()-1,aya.length()) + "\0";
						String[] ayaWords=finalAya.split(" ");
						 
						
						String ayaFinal = "";
						for (int nCount=0;nCount<ayaWords.length;nCount++)
						{
							ayaFinal +=new ArabicReshaper(ayaWords[nCount]).getReshapedWord();
							ayaFinal += "            ";
							//ayaFinal +=ayaWords[nCount];
						}						
						ayahList.add(ayaFinal);
						*/
					}
				}
				xpp.next();
			}
		}
		catch(Exception ex)
		{
			return ayahList;
		}
		return ayahList;
	}
}