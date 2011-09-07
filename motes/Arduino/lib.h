#ifndef lib_h
#define lib_h


#include <WProgram.h>
#include <inttypes.h>

#include <XbeeRadio.h>
#include <XBee.h>




#define IS_PIN_DIGITAL(p) ((p)>=2&&(p)<=13)




//define message id's

#define LED 0
#define SWITCH 1
#define LIGHT 2
#define TEMP 4
#define ON 1
#define OFF 0



#define SET_ON_O 0
#define SET_ON_M 1
#define SET_OFF_O 2
#define SET_OFF_M 3


class overAir
{
public:
	/*constructor*/
	overAir();
	/*xbee radio instance*/
	XBeeRadio xbee;
	
	/*begin serial with xbee radio*/
	void begin(long);
	/*define if there is incoming packet*/
	boolean available();

	uint8_t payload[6];
	
	uint16_t address;

	Tx16Request tx;
	
	/*process incoming packet*/
	void processPacket();

	void checkDigital();

	void checkAnalog();


private:

	byte digitalPinConfig[14];//1==input, 0==output

	byte digitalPinValue[14];

	byte digitalPinPreviousValue[14];

	byte digitalPinFunction[14];//1==switch, 2==...
	
	int analogPinValue[6];//keeps the reading value from a sensor

	byte analogPinFunction[6];//keeps the kind of sensor connected to the corresponding pin

	byte analogPinToReport[6];//keeps if a analogPin should be examined

};
extern overAir OverAirF;

#endif lib_h
