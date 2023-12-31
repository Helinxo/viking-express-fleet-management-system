package se.lu.ics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import se.lu.ics.models.LargeTruck;
import se.lu.ics.models.MediumTruck;
import se.lu.ics.models.Van;
import se.lu.ics.models.Vehicle;
import se.lu.ics.models.Service;
import se.lu.ics.models.ServiceHistory;
import se.lu.ics.models.WorkShop;
import se.lu.ics.models.MaintenanceSchedule;
import se.lu.ics.models.FleetManager;
import se.lu.ics.models.Service.PartType;
import se.lu.ics.models.Service.ServiceType;






    public class App {

        public static void main(String[] args) {

            // Create a new fleet manager
            FleetManager fleetManager = new FleetManager();


            // Create a new workshop

            WorkShop workShop1 = new WorkShop("W1", "Eriks verkstad", "Eriksvägen 1", true);
            WorkShop workShop2 = new WorkShop("W2", "Björns verkstad", "Eriksvägen 1", false);

            // add the workshop to the fleet manager

            fleetManager.addWorkShop(workShop1);
            fleetManager.addWorkShop(workShop2);

            // Create all different vehicles

            LargeTruck largeTruck = new LargeTruck("FH16", "Volvo", 2019, "Malmö", 10000, LocalDate.of(2019, 10, 10), 10000, 10000);
            MediumTruck mediumTruck = new MediumTruck("FH16", "Volvo", 2019, "Malmö", 10000, LocalDate.of(2019, 10, 10), 5000, 10000);
            Van van = new Van("FH16", "Volvo", 2019, "Malmö", 10000, LocalDate.of(2019, 10, 10), 1000, 10000);


            // Add all vehicles to the fleet manager

            fleetManager.addVehicle(largeTruck);
            fleetManager.addVehicle(mediumTruck);
            fleetManager.addVehicle(van);


        

            // Create a new service history

            // Create service histories and maintenance schedules for each vehicle
ServiceHistory serviceHistoryLargeTruck = new ServiceHistory();
MaintenanceSchedule maintenanceScheduleLargeTruck = new MaintenanceSchedule(serviceHistoryLargeTruck, workShop1);
largeTruck.setMaintenanceSchedule(maintenanceScheduleLargeTruck);

ServiceHistory serviceHistoryMediumTruck = new ServiceHistory();
MaintenanceSchedule maintenanceScheduleMediumTruck = new MaintenanceSchedule(serviceHistoryMediumTruck, workShop2);
mediumTruck.setMaintenanceSchedule(maintenanceScheduleMediumTruck);

ServiceHistory serviceHistoryVan = new ServiceHistory();
MaintenanceSchedule maintenanceScheduleVan = new MaintenanceSchedule(serviceHistoryVan, workShop1);
van.setMaintenanceSchedule(maintenanceScheduleVan);

            

       
            
    


            // add service to vehicle 1

            Service service1 = new Service(ServiceType.OIL_CHANGE, "Regular service", 10000, LocalDate.of(2019, 10, 10), PartType.OILFILTER, largeTruck, workShop1);
            Service service2 = new Service(ServiceType.ANNUAL_MAINTENANCE, "Regular service", 10000, LocalDate.of(2019, 10, 10), PartType.OILFILTER, mediumTruck, workShop1);
            Service service3 = new Service(ServiceType.BI_ANNUAL_MAINTENANCE, "Regular service", 10000, LocalDate.of(2019, 10, 10), PartType.OILFILTER, van, workShop1);
            Service service4 = new Service(ServiceType.BI_ANNUAL_MAINTENANCE, "Regular service", 10000, LocalDate.of(2019, 10, 10), PartType.ENGINE, largeTruck, workShop2);

            // add service to service history

            
           
            fleetManager.setServiceHistory(largeTruck, serviceHistoryLargeTruck);
            
          
            fleetManager.setServiceHistory(mediumTruck, serviceHistoryMediumTruck);
            
           
            fleetManager.setServiceHistory(van, serviceHistoryVan);
            


            serviceHistoryLargeTruck.addService(service1);
            serviceHistoryLargeTruck.addService(service4);
            serviceHistoryMediumTruck.addService(service2);
            serviceHistoryVan.addService(service3);

            

        // print parts


        

       
   




        


          

            

            
            

    }
}
       

   

       


      
      





    




         
    
  
    
