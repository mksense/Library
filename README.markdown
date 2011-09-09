#**Library**

The repository contains all the source files required to program and control Arduino and sunSPOT. 


The most recent technical report published at:

http://pci2011.teiwm.gr/

Please cite this work in scientific papers as:

Akribopoulos Orestis, Georgitzikis Vasileios, Protopapa Anastasia and Chatzigiannakis Ioannis, Building a Platform-agnostic Wireless Network of Interconnected Smart Objects, in: 15th Panhellenic Conference on Informatics with international 
participation (PCI 2011), IEEE, Kastoria, Greece, 2011.


#**What do I need?**

In order to program and control Arduino and sunSPOT using **mkSense/Library**, you must first deploy **mkSense/mac** by executing the following steps: 

**Deploy mkSense/mac radio stack on motes**

 * sunSPOT: 
   * ??
   * ??

 * Arduino board: 
   * place the XBeeRadio file in the *libraries* subdirectory of your default Arduino sketch directory.

**Deploy mkSense/mac radio stack on the Controller**

On the Controller (your PC) you can use as a Gate either a sunSPOT BaseStation or a XBee module. The deploying steps for each one are:

* sunSPOT BaseStation Gate: 
 * connect the BaseStation to your PC and do ```ant flashlibrary``` 

* XBee Gate: 
  * inside xbee/javaAPI folder do ```ant compile jar``` to create distributive.

#**Using mkSense/Library**

Since you deployed **mkSense/mac** you must do the following steps to be able to program and control the motes of your desire.  

**mkSense/Library on motes**

1. Deploy program created for sunSPOTs by doing:
 ``` ant deploy -Dport=/path/to/port ``` 

2. Deploy program created for Arduino board by placing *lib.cpp* and *lib.h* files in the *libraries* subdirectory of your default Arduino sketch directory, then upload to the Arduino board the sketch.pde file

**mkSense/Library on the Controller**

* If sunSPOT BaseStation is used as Gate you should use the source provided at sunSPOT-Gate file. Do ```ant compile jar``` to create distributive and import it in your IDE. The basedir of your application should have the *build.xml* and *build.properties* file in order to run it. In *build.properties* file you define your main class and any additional distributives you use. Run your application by doing ``` ant host-run -Dport=/path/to/basestationPort ```

* If XBee module is used as Gate you should use the source provided at xbee-Gate file. Do ```ant compile jar``` to create distributive and import it in your IDE. 



 
  
