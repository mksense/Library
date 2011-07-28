#include <XbeeRadio.h>
#include <lib.h>
#include <XBee.h>


void setup(){

  OverAirF.begin(9600);

}                                          

void loop(){
  
  //check digital pins and send messages
  OverAirF.checkDigital();
  
  
  while(OverAirF.available()){
    Serial.println("Something available");
    OverAirF.processPacket();
  }
  
  OverAirF.checkAnalog();
}

