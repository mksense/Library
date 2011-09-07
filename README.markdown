#**Library**

The repository contains all the source files required to program and control Arduino and sunSPOT devices. 


The most recent technical report published at:

http://pci2011.teiwm.gr/

Please cite this work in scientific papers as:

Akribopoulos Orestis, Georgitzikis Vasileios, Protopapa Anastasia and Chatzigiannakis Ioannis, Building a Platform-agnostic Wireless Network of Interconnected Smart Objects, in: 15th Panhellenic Conference on Informatics with international 
participation (PCI 2011), IEEE, Kastoria, Greece, 2011.


#**What do I need?**

In order to program and control Arduino and sunSPOT using **mksense/Library**, you must first deploy **mksense/mac** by executing the following steps: 


Deploy **mksense/mac** radio stack in motes
--------------------------------------

* Sun Spot: 
  * ??
  * ??

* Arduino board: 
  * place the XBeeRadio file in the libraries subdirectory of your default sketch directory.

Deploy **mksense/mac** radio stack in the Controller
------------------------------------------------------------

You can use **mksense/Library** either with SunSpot BaseStation gate or with XBee gate. The deploying steps are: 

* SunSPOT basestation Gate: 
 * do ```ant flashlibrary``` 
 * import sunSPOT libraries in your IDE.

* XBee Gate: 
  * inside javaAPI folder do ```ant compile jar``` to create distributive and import this in you IDE.


#**Using mksense/Library**

Since you deployed **mksense/mac** you must do the following steps to be able to program and control the motes of your desire.  

**mksense/Library and motes**
----------------------------------------

1. Deploy program created for SunSPOTs by doing:
 ``` ant deploy-Dport=/path/to/port ``` 

2. Deploy program created for Arduino board by placing *lib.cpp* and *lib.h* files in the libraries subdirectory of your default sketch directory, and then upload to the Arduino board the sketch.pde file

**mksense/Library and Controller**
-----------------------------------------------------------

* If SunSpot BaseStation is used as Gate you should use the source provided at sunSPOT-Gate file. Do ```ant compile jar``` to create distributive and import it in your IDE. The basedir of your application should have the *build.xml* and *build.properties* file in order to run it. In *build.properties* file you define your main class and any additional distributives you use. Run your application by doing ``` ant host-run -Dport=/path/to/basestationPort ```

* If XBee module is used as Gate you should use the source provided at xbee-Gate file. Do ```ant compile jar``` to create distributive and import it in your IDE. 



 
  
