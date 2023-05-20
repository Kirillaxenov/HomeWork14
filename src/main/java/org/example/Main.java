package org.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Ticket ticket = new Ticket("3", "51", 7, 4, 5);
        Ticket ticket1 = new Ticket("2", "14", 6, 7, 10);
        Ticket ticket2 = new Ticket("6", "65", 145, 90, 11);
        Ticket ticket3 = new Ticket("7", "75", 154, 90, 11);
        Ticket ticket4 = new Ticket("1", "57", 1, 90, 11);
        Ticket ticket5 = new Ticket("4", "h5", 17, 90, 11);
        Ticket ticket6 = new Ticket("5", "4342", 97, 90, 11);
       TicketTimeComparator ticketTimeComparator = new TicketTimeComparator();
        Ticket[] tic = {ticket1,ticket,ticket2,ticket3,ticket4,ticket5,ticket6};
        Arrays.sort(tic,ticketTimeComparator);

        }
    }

