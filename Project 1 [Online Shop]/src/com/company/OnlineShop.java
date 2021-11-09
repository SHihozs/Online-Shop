//Yanisa    Suphatsathienkul 6213196 EGCO
//Phumikorn Sereesantiwong   6213208 EGCO

package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class typeException extends Exception
{
    public typeException(String[] buf)
    {
        System.out.printf("Input Error : ");
        for (int j = 0; j < buf.length; j++)
        {
            System.out.printf("%-8s ",buf[j].trim());
        }
        System.out.printf("\n");

        buf[1] = "R";

        System.out.printf("Correction  : ");
        for (int j = 0; j < buf.length; j++)
        {
            System.out.printf("%-8s ",buf[j].trim());
        }
        System.out.printf("\n\n");
    }
}

class product implements Comparable<product>
{
    private String name;
    private int price;
    private int weight;

    private int totalSalesInCash;
    private int totalSalesInUnits;

    public product(String name, int price, int weight)
    {
        this.name = name;
        this.price = price;
        this.weight = weight;

        totalSalesInCash = 0;
        totalSalesInUnits = 0;
    }

    //==========Setter==========//

    public void setTotalSalesInCash(int totalSalesInCash) { this.totalSalesInCash = totalSalesInCash; }

    public void setTotalSalesInUnits(int totalSalesInUnits) { this.totalSalesInUnits = totalSalesInUnits; }

    //==========Getter==========//

    public String getName() { return name; }

    public int getPrice() { return price; }

    public int getWeight() { return weight; }

    public int getTotalSalesInCash() { return totalSalesInCash; }

    public int getTotalSalesInUnits() { return totalSalesInUnits; }

    //==========CalculateProductSale==========//

    public void calculateProductSale(ArrayList<customers> cus, int j)
    {
        int sale = 0;
        int unit = 0;


        for (int k = 0; k < cus.size(); k++)
        {
            sale += cus.get(k).getOrder().get(j) * price;
            unit += cus.get(k).getOrder().get(j);
        }

        totalSalesInCash  = sale;
        totalSalesInUnits = unit;
    }

    //==========Comparable==========//

    public int compareTo(product other)
    {
        if(other.getTotalSalesInCash()==this.getTotalSalesInCash())
        {
            return other.getTotalSalesInUnits() - this.getTotalSalesInUnits();
        }
        else
        {
            return other.getTotalSalesInCash() - this.getTotalSalesInCash();
        }

    }

    public void printSortedProduct()
    {
        System.out.printf("%-20s total sales = %,6d B, %,4d units \n",name,totalSalesInCash,totalSalesInUnits);
    }
}

class postage
{
    private Character type;
    private int minWeight;
    private int maxWeight;
    private int rate;

    public postage(Character type, int minWeight, int maxWeight, int rate)
    {
        this.type = type;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.rate = rate;
    }

    //==========Getter==========//

    public Character getType() { return type; }

    public int getMinWeight() { return minWeight; }

    public int getMaxWeight() { return maxWeight; }

    public int getRate() { return rate; }
}

class customers implements Comparable<customers>
{
    private String name;
    private ArrayList<Integer> order;
    private Character type;

    private int totalBill;
    private int totalWeight;
    private int productPrice;
    private int postage;

    public customers(String name, ArrayList<Integer> order, Character type)
    {
        this.name = name;
        this.order = order;
        this.type = type;

        this.totalBill = 0;
        this.totalWeight = 0;
        this.productPrice = 0;
        this.postage = 0;
    }

    //==========Setter==========//

    public void setTotalBill(int totalBill) { this.totalBill = totalBill; }

    public void setTotalWeight(int totalWeight) { this.totalWeight = totalWeight; }

    public void setProductPrice(int productPrice) { this.productPrice = productPrice; }

    public void setPostage(int postage) { this.postage = postage; }

    //==========Getter==========//

    public String getName() { return name; }

    public ArrayList<Integer> getOrder() { return order; }

    public Character getType() { return type; }

    public int getTotalBill() { return totalBill; }

    public int getTotalWeight() { return totalWeight; }

    public int getProductPrice() { return productPrice; }

    public int getPostage() { return postage; }

    //==========CalculateCustomerOrder==========//

    public void calculateCustomerOrder(ArrayList<product> pro,ArrayList<postage> pos)
    {
        for (int i = 0; i < pro.size(); i++)
        {
            productPrice += (pro.get(i).getPrice() * order.get(i));
            totalWeight += (pro.get(i).getWeight() * order.get(i));
        }

        for(int j = 0; j < pos.size(); j++)
        {

            if(type=='E' && pos.get(j).getType()=='E')
            {
                if(totalWeight > pos.get(j).getMinWeight() && totalWeight <= pos.get(j).getMaxWeight())
                {
                    postage = pos.get(j).getRate();
                }
            }
            else if(type=='R' && pos.get(j).getType()=='R' )
            {
                if(totalWeight > pos.get(j).getMinWeight() && totalWeight <= pos.get(j).getMaxWeight())
                {
                    postage = pos.get(j).getRate();
                }
            }
        }

        totalBill = postage + productPrice;
    }

    //==========Comparable==========//

    public int compareTo(customers other)
    {
        if(this.getTotalBill()==other.getTotalBill())
        {
            return this.getName().compareToIgnoreCase(other.getName());
        }
        else
        {
            return other.getTotalBill() - this.getTotalBill();
        }
    }

    //==========Print==========//

    public void printProcessOrder(ArrayList<product> pro)
    {
        System.out.printf("%-8s >>   ",name);
        for(int i = 0; i < pro.size() ; i++)
        {
            System.out.printf("%s (%2d)     ",pro.get(i).getName(),order.get(i));
        }
        System.out.printf("\n");
        System.out.printf("              product price = %,-10d total weight = %,-5d \n",productPrice,totalWeight);
        System.out.printf("              postage (%s)   = %,-10d total bill   = %,-5d \n\n",type,postage,totalBill);
    }

    public void printSortedBill()
    {
        System.out.printf("%-6s     bill = %,6d \n",name,totalBill);
    }
}

public class OnlineShop {

    static ArrayList<product>   pro = new ArrayList<>();
    static ArrayList<postage>   pos = new ArrayList<>();
    static ArrayList<customers> cus = new ArrayList<>();

//====================ReadFile====================//

    public static void readFileProducts(Scanner scan1)
    {
        while(scan1.hasNext())
        {
            String line = scan1.nextLine();
            String[] buf = line.split(",");
            int price  = Integer.parseInt(buf[1].trim());
            int weight = Integer.parseInt(buf[2].trim());
            product p = new product(buf[0].trim(),price,weight);
            pro.add(p);
        }
    }
    public static void readFilePostages(Scanner scan2)
    {
        while(scan2.hasNext())
        {
            String line = scan2.nextLine();
            String[] buf = line.split(",");
            char type = buf[0].charAt(0);
            int minWeight = Integer.parseInt(buf[1].trim());
            int maxWeight;
            int rate = Integer.parseInt(buf[3].trim());
            if(buf[2].trim().equals("inf"))
            {
                maxWeight = (int) Double.POSITIVE_INFINITY;
            }
            else {
                maxWeight = Integer.parseInt(buf[2].trim());
            }
            postage p = new postage(type,minWeight,maxWeight,rate);
            pos.add(p);
        }
    }
    public static void readFileCustomers(Scanner scan3)
    {
        while(scan3.hasNext())
        {
            String line = scan3.nextLine();
            String[] buf= line.split(",");
            char type = buf[1].trim().charAt(0);

            try
            {
                if (type != 'E' && type != 'R')
                {
                    throw new typeException(buf);
                }
            }
            catch (typeException e)
            {
                type = 'R';
            }

            try
            {
                if(buf.length<7||buf.length>7)
                {
                    throw new ArrayIndexOutOfBoundsException();
                }
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.printf("Input Error : ");
                for (int j = 0; j < buf.length; j++)
                {
                    System.out.printf("%-8s ",buf[j].trim());
                }
                System.out.printf("\n");

                if(buf.length<7)
                {
                    String[] copy = new String[7];
                    System.arraycopy(buf,0,copy,0,buf.length);

                    buf = copy;
                }
                else
                {
                    String[] copy = new String[7];
                    System.arraycopy(buf,0,copy,0,7);

                    buf = copy;
                }

                for (int i = 0; i < buf.length; i++)
                {
                    if(buf[i]==null)
                    {
                        buf[i] = "0";
                    }
                }

                System.out.printf("Correction  : ");
                for (int j = 0; j <= 6; j++)
                {
                    System.out.printf("%-8s ",buf[j].trim());
                }
                System.out.printf("\n\n");
            }

            ArrayList<Integer> order = new ArrayList<>();

            for (int i = 2; i < buf.length; i++)
            {
                int o;
                try
                {
                    o = Integer.parseInt(buf[i].trim());
                    if(o<0)
                    {
                        throw new NumberFormatException();
                    }
                }
                catch (NumberFormatException e)
                {
                    System.out.printf("Input Error : ");
                    for (int j = 0; j < buf.length; j++)
                    {
                        System.out.printf("%-8s ",buf[j].trim());
                    }
                    System.out.printf("\n");

                    buf[i] = "0";
                    o = 0;

                    System.out.printf("Correction  : ");
                    for (int j = 0; j <= 6; j++)
                    {
                        System.out.printf("%-8s ",buf[j].trim());
                    }
                    System.out.printf("\n\n");
                }
                order.add(o);
            }

            customers c = new customers(buf[0].trim(),order,type);
            cus.add(c);
        }
    }


//====================Process====================//

    public static void process()
    {
        for (customers cc:cus)
        {
            cc.calculateCustomerOrder(pro,pos);
        }

        int j=0;

        for (product pp:pro)
        {
            pp.calculateProductSale(cus,j);
            j++;
        }
    }

//====================PrintOutput====================//

    public static void printOutput()
    {
        System.out.println("---- Process order ----");

        for(customers cc:cus)
        {
            cc.printProcessOrder(pro);
        }

        Collections.sort(cus);

        System.out.println("----- Sort customers by total bill -----");

        for(customers cc:cus)
        {
            cc.printSortedBill();
        }

        Collections.sort(pro);

        System.out.println();
        System.out.println("----- Sort products by total sales in cash -----");

        for(product pp:pro)
        {
            pp.printSortedProduct();
        }
    }

//====================Main====================//

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String file;

        //==========Read File Products==========//

        boolean fileFounded = false;
        while(!fileFounded)
        {
            System.out.println("============================================================");
            System.out.println("ENTER FILE NAME FOR PRODUCTS        : ");
            file = input.nextLine();

            try(Scanner scan1 = new Scanner(new File(file));)
            {
                fileFounded = true;
                readFileProducts(scan1);
            }
            catch (FileNotFoundException e)
            {
                System.out.println(e);
                System.out.println("PLEASE TRY AGAIN");
            }
        }

        //==========Read File Postages==========//

        fileFounded = false;
        while(!fileFounded)
        {
            System.out.println("============================================================");
            System.out.println("ENTER FILE NAME FOR POSTAGE RATES   : ");
            file = input.nextLine();

            try(Scanner scan2 = new Scanner(new File(file)))
            {
                fileFounded = true;
                readFilePostages(scan2);
            }
            catch (FileNotFoundException e)
            {
                System.out.println(e);
                System.out.println("PLEASE TRY AGAIN");
            }
        }

        //==========Read File Customers==========//

        fileFounded = false;
        while(!fileFounded)
        {
            System.out.println("============================================================");
            System.out.println("ENTER FILE NAME FOR CUSTOMER ORDERS : ");
            file = input.nextLine();
            System.out.println("============================================================");
            System.out.println();

            try(Scanner scan3 = new Scanner(new File(file)))
            {
                fileFounded = true;
                readFileCustomers(scan3);
            }
            catch (FileNotFoundException e)
            {
                System.out.println(e);
                System.out.println("PLEASE TRY AGAIN");
            }
        }

        System.out.println("============================================================");

        //==========Process===========//

        process();

        //===========PrintOutput===========//

        printOutput();
    }
}
