package com.manning.siia.batch;

/**
 * @author Marius Bogoevici
 */
public class Notification
{
   private String message;

   private boolean failure;

   public Notification(String message, boolean failure)
   {
      this.message = message;
      this.failure = failure;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

   public boolean isFailure()
   {
      return failure;
   }

   public void setFailure(boolean failure)
   {
      this.failure = failure;
   }
}
