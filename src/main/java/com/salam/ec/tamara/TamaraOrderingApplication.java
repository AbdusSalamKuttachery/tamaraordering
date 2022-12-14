package com.salam.ec.tamara;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TamaraOrderingApplication implements CommandLineRunner
{

   public static void main(final String[] args)
   {
      SpringApplication.run(TamaraOrderingApplication.class, args);
   }

   @Override
   public void run(final String... args) throws Exception
   {
      System.out.println("Salam - taking off!");
   }

}
