package Main.java;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Vertices vertices = new Vertices();
        Read_Informations_From_CSV_File read_informations_from_csv_file = new Read_Informations_From_CSV_File();
        read_informations_from_csv_file.Read_People_Informations(vertices);
        read_informations_from_csv_file.Read_Account_Informations(vertices);
        read_informations_from_csv_file.Read_Homes_Informations(vertices);
        read_informations_from_csv_file.Read_Cars_Informations(vertices);
        read_informations_from_csv_file.Read_Phones_Informations(vertices);

        Edges edges = new Edges();
        read_informations_from_csv_file.Read_Ownership_Informations(edges);
        read_informations_from_csv_file.Read_Transactions_Informations(edges);
        read_informations_from_csv_file.Read_Calls_Informations(edges);
        read_informations_from_csv_file.Read_Relationships_Informations(edges);

        //WriteToFile write = new WriteToFile(vertices,edges);
        Relate_Vertices_With_Edges relate_vertices_with_edges = new Relate_Vertices_With_Edges();
        relate_vertices_with_edges.Ownership_Edge(edges,vertices);
        int cnt = 1 ;
        for (People p : vertices.people){
            System.out.println(cnt);
            System.out.println(p.getFirst_name() + " " + p.getLast_name());
            p.printc();
            p.printh();
            System.out.println("............................");
            cnt ++ ;
        }

    }
}

class WriteToFile{

    Scanner input = new Scanner(System.in);

    public WriteToFile(Vertices vertices, Edges edges){

        System.out.println("Which one do you want to see?\n1-Vertices\n2-Edeges\n3-Both");
        int n = input.nextInt();
        if (n == 1){
            System.out.println("How many specifications do you want to see?");
            int n1 = input.nextInt();
            if (n1 == 5){
                Write_People(vertices.people);
                Write_Accounts(vertices.accounts);
                Write_Homes(vertices.homes);
                Write_Cars(vertices.cars);
                Write_Phones(vertices.phones);
            }
            else {
                System.out.println("Which one do you want to see?\n1-People\n2-Account\n3-Homes\n4-Cars\n5-Phones");
                for (int i=0; i<n1; i++){
                    int nv = input.nextInt();
                    if (nv == 1) Write_People(vertices.people);
                    else if (nv == 2) Write_Accounts(vertices.accounts);
                    else if (nv == 3)Write_Homes(vertices.homes);
                    else if (nv == 4)  Write_Cars(vertices.cars);
                    else if (nv == 5) Write_Phones(vertices.phones);
                }
            }
        }

        else if (n == 2){
            System.out.println("How many specifications do you want to see?");
            int n1 = input.nextInt();
            if (n1 == 5){
                Write_Ownership(edges.Ownership);
                Write_Transactions(edges.Transactions);
                Write_Calls(edges.Call);
                Write_Relationship(edges.Relationship);
            }
            else {
                System.out.println("Which one do you want to see?\n1-Ownership\n2-Transactions\n3-Call\n4-Relationship");
                for (int i=0; i<n1; i++){
                    int ne = input.nextInt();
                    if (ne == 1) Write_Ownership(edges.Ownership);
                    else if (ne == 2)  Write_Transactions(edges.Transactions);
                    else if (ne == 3)Write_Calls(edges.Call);
                    else if (ne == 4)  Write_Relationship(edges.Relationship);
                }
            }
        }

        else {
            Write_People(vertices.people);
            Write_Accounts(vertices.accounts);
            Write_Homes(vertices.homes);
            Write_Cars(vertices.cars);
            Write_Phones(vertices.phones);
            Write_Ownership(edges.Ownership);
            Write_Transactions(edges.Transactions);
            Write_Calls(edges.Call);
            Write_Relationship(edges.Relationship);
        }
    }

    public void Write_People(HashSet<People> people){

        try {
            FileWriter writer = new FileWriter("E:\\People.txt");

            for (People p : people){
                writer.write("ID:"+ p.getSsn()+"\n"+"FirstName:"+ p.getFirst_name());
                writer.write("    LastName:"+ p.getLast_name()+"     Birthday:"+ p.getBirthday());
                writer.write("     City:"+ p.getCity()+"     Work:"+ p.getWork()+"\n\n");
            }

            writer.close();
            System.out.println("Successfully wrote to the people file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Accounts(HashSet<Account> accounts){

        try {
            FileWriter writer = new FileWriter("E:\\Accounts.txt");

            for (Account a : accounts){
                writer.write("ID:"+ a.getAccount_id()+"\n"+"Bank_Name:"+ a.getBank_name());
                writer.write("     IBAN:"+ a.getIBAN()+"     Ssn:"+ a.getSsn()+"\n\n");
            }

            writer.close();
            System.out.println("Successfully wrote to the Accounts file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Homes(HashSet<Home> homes){

        try {
            FileWriter writer = new FileWriter("E:\\Homes.txt");

            for (Home h : homes){
                writer.write("ID:"+ h.getPostal_code()+"\n"+"Ssn:"+ h.getSsn());
                writer.write("     Price:"+ h.getPrice()+"     Meters:"+ h.getSize()+"\n");
                writer.write("     Address"+h.getAddress()+"\n\n");
            }

            writer.close();
            System.out.println("Successfully wrote to the Homes file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Cars(HashSet<Car> cars){

        try {
            FileWriter writer = new FileWriter("E:\\Cars.txt");

            for (Car c : cars){
                writer.write("ID:"+ c.getPlate()+"\n"+"Model:"+ c.getModel());
                writer.write("     Color:"+ c.getColor()+"     Ssn:"+ c.getSsn()+"\n\n");
            }

            writer.close();
            System.out.println("Successfully wrote to the cars file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Phones(HashSet<Phone> phones){

        try {
            FileWriter writer = new FileWriter("E:\\Phones.txt");

            for (Phone p : phones){
                writer.write("ID:"+ p.getNumber()+"\n"+"Ssn:"+ p.getSsn());
                writer.write("     Operator:"+ p.getOperator()+"\n\n");
            }

            writer.close();
            System.out.println("Successfully wrote to the Phones file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Ownership(HashSet<Ownership> ownerships){

        try {
            FileWriter writer = new FileWriter("E:\\Ownership.txt");

            for (Ownership o : ownerships){
                writer.write("From:"+ o.getFrom());
                if (o.getToCar() != null) writer.write("   To:"+ o.getToCar()+"\n");
                else writer.write("   To:"+ o.getToHome()+"\n");
                writer.write("Ownership_id:"+ o.getOwnership_id()+"     Date:"+ o.getDate());
                writer.write("      Amount:"+ o.getAmount()+"\n\n");
            }

            writer.close();
            System.out.println("Successfully wrote to the Ownership file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Transactions(HashSet<Transactions> transactions){

        try {
            FileWriter writer = new FileWriter("E:\\Transactions.txt");

            for (Transactions t : transactions){
                writer.write("From:"+ t.getFrom()+"   To:"+ t.getTo()+"\n");
                writer.write("Transaction_id:"+ t.getTransaction_id());
                writer.write("      Date:"+ t.getDate()+"      Amount"+t.getAmount()+"\n\n");
            }

            writer.close();
            System.out.println("Successfully wrote to the Transactions file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Calls(HashSet<Call> calls) {

        try {
            FileWriter writer = new FileWriter("E:\\Calls.txt");

            for (Call c : calls) {
                writer.write("From:" + c.getFrom() + "   To:" + c.getTo() + "\n");
                writer.write("Call_id:" + c.getCall_id());
                writer.write("      Date:" + c.getDate() + "      Duration" + c.getDuration() + "\n\n");
            }

            writer.close();
            System.out.println("Successfully wrote to the calls file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Write_Relationship(HashSet<Relationship> relationships) {

        try {
            FileWriter writer = new FileWriter("E:\\Relationship.txt");

            for (Relationship r : relationships) {
                writer.write("From:" + r.getFrom() + "   To:" + r.getTo() + "\n");
                writer.write("Call_id:" + r.getRelation()+"      Date:" + r.getDate()+ "\n\n");
            }

            writer.close();
            System.out.println("Successfully wrote to the Relationship file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

class Read_Informations_From_CSV_File{

    public void Read_People_Informations(Vertices vertices){

        String line;
        try {

            BufferedReader br = new BufferedReader(new FileReader("E:\\people.csv"));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                People people = new People();
                people.setFirst_name(value[0].substring(1,value[0].length()-1));
                people.setLast_name(value[1].substring(1,value[1].length()-1));
                people.setSsn(Double.parseDouble(value[2].substring(1,value[2].length()-1)));
                people.setBirthday(value[3].substring(1,value[3].length()-1));
                people.setCity(value[4].substring(1,value[4].length()-1));
                people.setWork(value[5].substring(1,value[5].length()-1));
                vertices.New_People(people);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void Read_Account_Informations(Vertices vertices) {

        String line;
        try {

            BufferedReader br = new BufferedReader(new FileReader("E:\\accounts.csv"));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                Account account = new Account();
                account.setSsn(Double.parseDouble(value[0].substring(1,value[0].length()-1)));
                account.setBank_name(value[1].substring(1,value[1].length()-1));
                account.setIBAN(value[2].substring(1,value[2].length()-1));
                account.setAccount_id(Integer.parseInt(value[3].substring(1,value[3].length()-1)));
                vertices.New_Account(account);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Read_Homes_Informations(Vertices vertices){

        String csvFile = "E:\\homes.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int lineNumber = 0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if (lineNumber == 0) {
                    lineNumber++;
                    continue;
                }
                lineNumber++;
                Home home = new Home();
                // use comma as separator
                String[] Homee = line.split(cvsSplitBy);
                for (int h = 0; h < Homee.length-3; h++) {
                    String tmp0 = "";
                    for (int l = 1 ; l < Homee[h].length()-1 ; l++) tmp0 +=Homee[h].charAt(l);
                    Homee[h] = tmp0;
                    // System.out.println(h + " " +  country[h]);
                    if (h == 0) home.setSsn(Double.parseDouble(Homee[h]));
                    if (h == 1) home.setPrice(Double.parseDouble(Homee[h]));
                    if (h == 2) home.setPostal_code(Double.parseDouble(Homee[h]));
                    if (h == 3) home.setSize(Integer.parseInt(Homee[h]));
                }
                String tmp="" , tmp1 = "";
                int t = Homee.length-3 ;
                for (int h = Homee.length-3 ; h < Homee.length ; h ++) {
                    tmp += Homee[h];
                    //System.out.println(h +" " +tmp);
                }
                //System.out.println(t+"" +tmp);
                for (int l = 1 ; l < tmp.length()-1 ; l ++) tmp1+=tmp.charAt(l);
                tmp=tmp1;
                home.setAddress(tmp);
                //System.out.println("------------------------------------------------------");
                //System.out.println(home.Get_price());
                vertices.New_Home(home);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (br != null) {
                try {
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Read_Cars_Informations(Vertices vertices){

        String csvFile = "E:\\cars.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int lineNumber=0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if(lineNumber == 0) {
                    lineNumber++;
                    continue;
                }
                lineNumber++;
                String[] carr = line.split(cvsSplitBy);
                Car car = new Car();
                for (int h = 0 ; h < carr.length ; h ++ ){
                    String tmp0 = "";
                    for (int l = 1  ; l < carr[h].length() - 1 ; l ++) tmp0 += carr[h].charAt(l);
                    carr[h]=tmp0;
                    //System.out.println(carr[h]);
                    if (h == 0) car.setPlate(carr[h]);
                    if (h == 1) car.setSsn(Double.parseDouble(carr[h]));
                    if (h == 2) car.setModel(carr[h]);
                    if (h == 3) car.setColor(carr[h]);
                }
                ///System.out.println("------------------------------------------------------");
                //System.out.println(car.Get_plate());
                vertices.New_Car(car);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (br != null) {
                try {
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Read_Phones_Informations(Vertices vertices){

        String csvFile = "E:\\phones.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int lineNumber=0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if(lineNumber == 0) {
                    lineNumber++;
                    continue;
                }
                lineNumber++;
                Phone phone = new Phone();
                String[] phonee = line.split(cvsSplitBy);
                for (int h = 0 ; h < phonee.length ; h ++ ) {
                    //System.out.println(phonee[h]);
                    String tmp0 = "";
                    for (int l = 1 ; l < phonee[h].length()-1 ; l ++) tmp0+=phonee[h].charAt(l);
                    phonee[h] = tmp0;
                    if (h == 0) phone.setSsn(Double.parseDouble(phonee[h]));
                    if (h == 1) phone.setNumber(Double.parseDouble(phonee[h]));
                    if (h == 2) phone.setOperator(phonee[h]);
                }
                //System.out.println("------------------------------------------------------");
                vertices.New_Phone(phone);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (br != null) {
                try {
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Read_Ownership_Informations(Edges edges){

        String line;
        try {

            BufferedReader br = new BufferedReader(new FileReader("E:\\ownerships.csv"));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                Ownership ownership = new Ownership();
                ownership.setFrom(Double.parseDouble(value[0].substring(1,value[0].length()-1)));
                if (value[1].length() > 9) ownership.setToHome(Double.parseDouble(value[1].substring(1, value[1].length() - 1)));
                else ownership.setToCar(value[1].substring(1, value[1].length() - 1));
                ownership.setOwnership_id(value[2].substring(1,value[2].length()-1));
                ownership.setDate(value[3].substring(1,value[3].length()-1));
                ownership.setAmount(Double.parseDouble(value[4].substring(1,value[4].length()-1)));
                edges.New_Ownership(ownership);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void Read_Transactions_Informations(Edges edges){

        String line;
        try {

            BufferedReader br = new BufferedReader(new FileReader("E:\\transactions.csv"));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                Transactions transactions = new Transactions();
                transactions.setFrom(value[0].substring(1,value[0].length()-1));
                transactions.setTo(value[1].substring(1, value[1].length() - 1));
                transactions.setTransaction_id(Double.parseDouble(value[2].substring(1,value[2].length()-1)));
                transactions.setDate(value[3].substring(1,value[3].length()-1));
                transactions.setAmount(Double.parseDouble(value[4].substring(1,value[4].length()-1)));
                edges.New_Transactions(transactions);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void Read_Calls_Informations(Edges edges){

        String csvFile = "E:\\calls.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int lineNumber=0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if(lineNumber == 0) {
                    lineNumber++;
                    continue;
                }
                lineNumber++;
                Call call = new Call();
                String[] calll = line.split(cvsSplitBy);
                for (int h = 0 ; h < calll.length ; h ++ ) {
                    String tmp0 = "";
                    for (int l = 1 ; l < calll[h].length()-1 ; l ++) tmp0+=calll[h].charAt(l);
                    calll[h] = tmp0;
                    if (h == 0) call.setFrom(Double.parseDouble(calll[h]));
                    if (h == 1) call.setTo(Double.parseDouble(calll[h]));
                    if (h == 2) call.setCall_id(Double.parseDouble(calll[h]));
                    if (h == 3) call.setDate(calll[h]);
                    if (h == 4) call.setDuration(calll[h]);
                }
                edges.New_Call(call);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Read_Relationships_Informations(Edges edges){

        String csvFile = "E:\\relationships.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int lineNumber=0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if(lineNumber == 0) {
                    lineNumber++;
                    continue;
                }
                lineNumber++;
                Relationship relationship = new Relationship();
                String[] relationshipee = line.split(cvsSplitBy);
                for (int h = 0 ; h < relationshipee.length ; h ++ ) {
                    String tmp0 = "";
                    for (int l = 1 ; l < relationshipee[h].length()-1 ; l ++) tmp0 += relationshipee[h].charAt(l);
                    relationshipee[h] = tmp0;
                    if (h == 0) relationship.setFrom(Double.parseDouble(relationshipee[h]));
                    if (h == 1) relationship.setTo(Double.parseDouble(relationshipee[h]));
                    if (h == 2) relationship.setRelation(relationshipee[h]);
                    if (h == 3) relationship.setDate(relationshipee[h]);
                }
                edges.New_Relationship(relationship);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Vertices{
    //vertices:
    HashSet<People> people = new HashSet<People>();
    HashSet<Account> accounts = new HashSet<Account>();
    HashSet<Home> homes = new HashSet<Home>();
    HashSet<Car> cars = new HashSet<Car>();
    HashSet<Phone> phones = new HashSet<Phone>();
    //keys of vertices:
    ArrayList<Double> people_Keys = new ArrayList<>();
    ArrayList<String> accounts_Keys = new ArrayList<>();
    ArrayList<Double> homes_keys = new ArrayList<>();
    ArrayList<String> cars_keys = new ArrayList<>();
    ArrayList<Double> phones_keys = new ArrayList<>();
    //mathodes:
    public void New_People(People p){ people.add(p); }
    public void New_Account(Account account){ accounts.add(account); }
    public void New_Home(Home home){ homes.add(home); }
    public void New_Car(Car car){ cars.add(car);}
    public void New_Phone(Phone phone){ phones.add(phone);}

    public int cnt_People(){ return people.size(); }
    public int cnt_Acount(){ return accounts.size(); }
    public int cnt_Homes(){ return homes.size(); }
    public int cnt_Cars() { return cars.size(); }
    public int cnt_Phones() { return phones.size(); }

    public void Add_Keys_Of_People(Double ssn) { people_Keys.add(ssn);}
    public void Add_Keys_Of_Accoount(String IBAN) { accounts_Keys.add(IBAN); }
    public void Add_Keys_Of_Homes(Double postal_code) { homes_keys.add(postal_code);}
    public void Add_Keys_Of_Cars(String plate) { cars_keys.add(plate); }
    public void Add_Keys_Of_Phones(Double number) { phones_keys.add(number); }
}

class People{
    private String first_name;
    private String last_name;
    private double ssn;
    private LocalDate birthday;
    private String city;
    private String work;
    private String address;
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Home> homes = new ArrayList<>();

    public void Add_Car(Car car){ cars.add(car);}
    public void Add_Home(Home home) {homes.add(home); }

    public void printc(){
        for (int i = 0 ; i < cars.size() ; i ++) System.out.println(cars.get(i).getPlate());
    }
    public void printh(){
        for (int i = 0 ; i < homes.size() ; i ++) System.out.println(homes.get(i).getPostal_code());
    }
    public void setFirst_name(String first_name) { this.first_name = first_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }
    public void setSsn(Double ssn) { this.ssn = ssn; }
    public void setCity(String city) { this.city = city; }
    public void setWork(String work) { this.work = work; }
    public void setAddress(String address){ this.address = address; }
    public void setBirthday(String birthday ){
        String date[] = birthday.split("-");
        this.birthday = LocalDate.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
    }

    public String getFirst_name() { return first_name; }
    public String getLast_name() { return last_name; }
    public double getSsn() { return ssn; }
    public LocalDate getBirthday() { return birthday; }
    public String getCity() { return city; }
    public String getWork() { return work; }



}

class Account{
    private double ssn;
    private String bank_name;
    private String IBAN;
    private int account_id;

    public void setSsn(double ssn) { this.ssn = ssn; }
    public void setBank_name(String bank_name) { this.bank_name = bank_name; }
    public void setIBAN(String IBAN) { this.IBAN = IBAN; }
    public void setAccount_id(int account_id) { this.account_id = account_id; }

    public double getSsn() { return ssn; }
    public String getBank_name() { return bank_name; }
    public String getIBAN() { return IBAN; }
    public int getAccount_id() { return account_id; }
}

class Home{
    private double ssn;
    private double price;
    private double postal_code;
    private int size;
    private String address;

    public void setSsn(double ssn) { this.ssn = ssn; }
    public void setPrice(double price) { this.price = price; }
    public void setPostal_code(double postal_code) { this.postal_code = postal_code; }
    public void setSize(int size) { this.size = size; }
    public void setAddress(String address) { this.address = address; }

    public double getSsn() { return ssn; }
    public double getPrice() { return price; }
    public double getPostal_code() { return postal_code; }
    public int getSize() { return size; }
    public String getAddress() { return address; }

}

class Car{
    private String plate;
    private double ssn;
    private String model;
    private String color;

    public void setPlate(String plate) { this.plate = plate; }
    public void setSsn(double ssn) { this.ssn = ssn; }
    public void setModel(String model) { this.model = model; }
    public void setColor(String color) { this.color = color; }

    public String getPlate() { return plate; }
    public double getSsn() { return ssn; }
    public String getModel() { return model; }
    public String getColor() { return color; }
}

class Phone{
    private double ssn;
    private double number;
    private String operator;

    public void setSsn(double ssn) { this.ssn = ssn; }
    public void setNumber(double number) { this.number = number; }
    public void setOperator(String operator) { this.operator = operator; }

    public double getSsn() { return ssn; }
    public double getNumber() { return number; }
    public String getOperator() { return operator; }
}


class Edges{

    HashSet<Ownership> Ownership = new HashSet<Ownership>();
    HashSet<Transactions> Transactions = new HashSet<Transactions>();
    HashSet<Call> Call = new HashSet<Call>();
    HashSet<Relationship> Relationship = new HashSet<Relationship>();

    ArrayList<String> Ownership_Keys = new ArrayList<>();
    ArrayList<Double> Transactions_Keys = new ArrayList<>();
    ArrayList<Double> Call_keys = new ArrayList<>();
    //private ArrayList<Double> Relationship_keys = new ArrayList<>();

    public void New_Ownership(Ownership ownership){ Ownership.add(ownership); }
    public void New_Transactions(Transactions transactions){ Transactions.add(transactions); }
    public void New_Call(Call call){ Call.add(call); }
    public void New_Relationship(Relationship relationship){ Relationship.add(relationship);}

    public int cnt_Ownership(){ return Ownership.size(); }
    public int cnt_Transactions(){ return Transactions.size(); }
    public int cnt_Call(){ return Call.size(); }
    public int cnt_Relationship() { return Relationship.size(); }

    public void Add_Keys_Of_Ownership(String ownership_id) {Ownership_Keys.add(ownership_id);}
    public void Add_Keys_Of_Transactions(Double transaction_id) { Transactions_Keys.add(transaction_id); }
    public void Add_Keys_Of_Call(Double call_id) {Call_keys.add(call_id);}
    //public void Add_Keys_Of_Relationship(String plate) { Relationship_keys.add(plate); }

}

class Ownership{
    private double from;
    private double toHome;
    private String toCar;
    private String ownership_id;
    private LocalDate date;
    private double amount;

    public void setFrom(double from) { this.from = from; }
    public void setToHome(double toHome) { this.toHome = toHome; }
    public void setToCar(String toCar) { this.toCar = toCar; }
    public void setOwnership_id(String ownership_id) { this.ownership_id = ownership_id; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setDate(String d ){
        String date[] = d.split("-");
        this.date = LocalDate.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
    }

    public double getFrom() { return from; }
    public double getToHome() { return toHome; }
    public String getToCar() { return toCar; }
    public String getOwnership_id() { return ownership_id; }
    public LocalDate getDate() { return date; }
    public double getAmount() { return amount; }
}

class Transactions{
    private String from;
    private String to;
    private double transaction_id;
    private LocalDate date;
    private double amount;

    public void setFrom(String from) { this.from = from; }
    public void setTo(String to) { this.to = to; }
    public void setTransaction_id(double transaction_id) { this.transaction_id = transaction_id; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setDate(String d ){
        String date[] = d.split("-");
        this.date = LocalDate.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
    }

    public String getFrom() { return from; }
    public String getTo() { return to; }
    public double getTransaction_id() { return transaction_id; }
    public LocalDate getDate() { return date; }
    public double getAmount() { return amount; }
}

class Call{
    private double from ;
    private double to ;
    private double call_id;
    private LocalDateTime date;
    private LocalTime duration;

    public void setFrom(double from) { this.from = from; }
    public void setTo(double to) { this.to = to; }
    public void setCall_id(double call_id) { this.call_id = call_id; }
    public void setDate(String date) { this.date= LocalDateTime.parse(date); }
    public void setDuration(String duration) { this.duration = LocalTime.parse(duration); }

    public double getFrom() { return from; }
    public double getTo() { return to; }
    public double getCall_id() { return call_id; }
    public LocalDateTime getDate() { return date; }
    public LocalTime getDuration() { return duration; }
}

class Relationship{
    private double from;
    private double to;
    private String relation;
    private LocalDate date;

    public void setFrom(double from) { this.from = from; }
    public void setTo(double to) { this.to = to; }
    public void setRelation(String relation) { this.relation = relation; }
    public void setDate(String d) {
        String date[] = d.split("-");
        this.date = LocalDate.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
    }

    public double getFrom() { return from; }
    public double getTo() { return to; }
    public String getRelation() { return relation; }
    public LocalDate getDate() { return date; }
}

class Relate_Vertices_With_Edges{
    public void Ownership_Edge(Edges edges , Vertices vertices) {
        for (Ownership ownership : edges.Ownership){
            for (People p : vertices.people) {
                if (ownership.getFrom() == p.getSsn()) {
                    if (ownership.getToCar()==null) {
                        for (Home home : vertices.homes) {
                            if (ownership.getToHome() == home.getPostal_code()) {
                                p.Add_Home(home);
                                break;
                            }
                        }
                    } else {
                        for (Car car : vertices.cars) {
                            if (ownership.getToCar().equals(car.getPlate())) {
                                p.Add_Car(car);
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
}