#include "WProgram.h"
#include "lib.h"

overAir::overAir()
{

for(int i=0;i<6;i++)
{
	payload[i]=0;
}

tx=Tx16Request(address,payload,sizeof(payload));


	//initialize the arrays that keep truck the digital pins
  for(int i=0;i<14;i++)
  {
    digitalPinValue[i]=0;
    digitalPinPreviousValue[i]=0;
    digitalPinConfig[i]=0;
    digitalPinFunction[i]=0;
  }
	//initialize arrays that keep truck of the analog pins
	for(int i=0;i<6;i++){
		analogPinValue[i]=0;
		analogPinFunction[i]=0;
		analogPinToReport[i]=0;	
	}
	
}

void overAir::begin(long speed)
{
	xbee.begin(speed);
	xbee.init();
}

boolean overAir::available()
{
	xbee.readPacket();
	if(xbee.getResponse().isAvailable() && xbee.getResponse().validPacket(100))
	{
		return true;
	}
	else 
		return false;
}



void overAir::checkDigital()
{ 
	//read the value of digital pins that are configured as inputs
 for(int i=2;i<14;i++)
  {
    if(digitalPinConfig[i]==1)
    {
      digitalPinValue[i]= digitalRead(i);
    }
  }

  for(int i=2;i<14;i++)
  {

    if(digitalPinValue[i]!=digitalPinPreviousValue[i])
    {
      payload[0]=digitalPinFunction[i]&0xff;
      payload[1]=i&0xff;
      payload[5]=digitalPinValue[i]&0xff;
      
      tx.setAddress16(address);

      xbee.send(tx,37);

      digitalPinPreviousValue[i]=digitalPinValue[i];

    }
  }


}

void overAir::checkAnalog()
{
	for(int i=0;i<6;i++){
		if(analogPinToReport[i]==1)
		{
			analogRead(i);
			delay(50);
			analogPinValue[i]=analogRead(i);
		}		
	}

	for(int i=0;i<6;i++)
	{
		if(analogPinToReport[i]==1)
		{
		payload[0]=analogPinFunction[i]&0xff;
		payload[1]=i&0xff;
		payload[2]=0&0xff;
		payload[3]=0&0xff;
		payload[4]=analogPinValue[i]>>8&0xff;
		payload[5]=analogPinValue[i]&0xff;
		
		
		tx.setAddress16(address);

		xbee.send(tx,37);

		analogPinToReport[i]=0;
		analogPinValue[i]=0;
		}
	}
}

void overAir::processPacket()
{
	int command=xbee.getResponse().getData(0);
	switch(command)
	{
	case LED:
	{
	        int action=xbee.getResponse().getData(1);
		if(action == SET_ON_O) 
		{
			int led=xbee.getResponse().getData(2);
			if(IS_PIN_DIGITAL(led))
			{		
				pinMode(led,OUTPUT);
				digitalWrite(led,HIGH);
			}
		}
		else if (action==SET_ON_M)
		{
			int led1=xbee.getResponse().getData(2);
			int led2=xbee.getResponse().getData(3);
			if(IS_PIN_DIGITAL(led1)&&IS_PIN_DIGITAL(led2))
			{
				for(int i=led1;i<=led2;i++)
				{
					pinMode(i,OUTPUT);
					digitalWrite(i,HIGH);			
				}	
			}	


		} 
		else if (action ==SET_OFF_O)
		{
			int led=xbee.getResponse().getData(2);
			
			if(IS_PIN_DIGITAL(led))
			{
				digitalWrite(led,LOW);
			}
		}
		else if(action ==SET_OFF_O)
		{
			int led1=xbee.getResponse().getData(2);
			int led2=xbee.getResponse().getData(3);
			if(IS_PIN_DIGITAL(led1)&&IS_PIN_DIGITAL(led2))
			{
				for(int i=led1;i<=led2;i++)
				{
					digitalWrite(i,LOW);
				}
				
			}
		}
		break;
	}case SWITCH:
	{
		xbee.getResponse().getRemoteAddress(address);
		 int action=xbee.getResponse().getData(1);
		if(action==ON)
		{
			int sw=xbee.getResponse().getData(2);
			pinMode(sw,INPUT);
			digitalPinConfig[sw]=1;
			digitalPinFunction[sw]=1;
		}
		if(action==OFF){
			int sw=xbee.getResponse().getData(2);
			digitalPinConfig[sw]=0;
			digitalPinFunction[sw]=0;
		}
		break;
	}case LIGHT:
	{
		xbee.getResponse().getRemoteAddress(address);
		int action =xbee.getResponse().getData(1);
		analogPinFunction[action]=LIGHT;
		analogPinToReport[action]=1;
		break;	
	}
	case TEMP:
	{
	xbee.getResponse().getRemoteAddress(address);
	int pin=xbee.getResponse().getData(1);
	analogPinFunction[pin]=TEMP;
	analogPinToReport[pin]=1;
	break;
	}

	}
}

overAir OverAirF;

