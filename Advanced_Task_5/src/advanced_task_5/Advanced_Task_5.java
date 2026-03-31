/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package advanced_task_5;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Leveno
 */
public class Advanced_Task_5 {
    public static String taskFile = "file.txt";

     // add new Task
    public static void addTask(ArrayList<String> list){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Task is ADDED to the List: ");
        String s = sc.nextLine();
        
        list.add(s);
        saveTask(list, taskFile);
    }
    
    
    // read the task of user's choice
    public static void readTask(ArrayList<String> list){
        if(list.isEmpty())
            System.out.println("No Task found to READ!!!");
        else{
            for(int i=0; i<list.size(); i++){
                System.out.println("Task" + (i+1) + " " + list.get(i));
            }
        }
    }
    
    
    // update the task of user's choice
    public static void updateTask(ArrayList<String> list){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Which Task to Update?  Task");
        int task = sc.nextInt();
        
        if((task-1) >= list.size() || (task-1) < 0)
            System.out.println("No Task Found to UPDATE!!");
        else{
            sc.nextLine();
            System.out.print("Enter New Task: ");
            String s = sc.nextLine();
        
            list.set(task-1, s);
            saveTask(list, taskFile);
        }
    }
    
    
    // delete the task of user's choice
    public static void deleteTask(ArrayList<String> list){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Which Task to Delete?  Task");
        int task = sc.nextInt();
        
        if((task-1) >= list.size() || (task-1) < 0)
            System.out.println("No Task Found to Delete!!");
        else{
            list.remove(task-1);
            saveTask(list, taskFile);
        }
    } 
    
    
    // load the tasks from the taskFile
    public static void loadTask(ArrayList<String> list, String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            return ;
        }
        
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            ArrayList<String> lines = new ArrayList<>();
            String line;
            
            while((line = reader.readLine()) != null){
                lines.add(line);
            }
            list.clear();
            list.addAll(lines);
        }catch(IOException e){
            System.out.println("Error: Tasks cannot be loaded to File: " + e.getMessage());
        }
    }
    
    
    // save the tasks to a taskFile
    public static void saveTask(ArrayList<String> list, String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))){
            for(String task : list){
                writer.write(task);
                writer.newLine();
            }
        }catch(IOException e){
            System.out.println("Error: Tasks cannot be saved to File: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        
        loadTask(list, taskFile);
        
        System.out.println("Select the choice: ");
        System.out.println("1. Add Task: ");
        System.out.println("2. Read Tasks: ");
        System.out.println("3. Update Task: ");
        System.out.println("4. Delete Task: ");
        System.out.println("5. Exit: ");
        
        do{
            System.out.print("Enter the Operation Choice: ");
            int choice = sc.nextInt();
            
            switch(choice){
                case 1: addTask(list);
                    break;
                    
                case 2: readTask(list);           
                    break;
                    
                case 3: updateTask(list);
                    break;
                    
                case 4: deleteTask(list);
                    break;
                    
                case 5: 
                    System.out.println("Exit the Console");
                    saveTask(list, taskFile);
                    System.exit(0);
                   
                default :
                    System.out.println("Wrong Choice!!!");
            }
            
            System.out.println();
        }while(true);
        
    }
    
}
