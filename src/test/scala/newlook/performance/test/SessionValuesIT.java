package newlook.performance.test;

import junit.framework.Assert;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by sreekanth.bongunuri on 12/07/16.
 */
public class SessionValuesIT {

    File folder = new File("target/gatling");
    File[] listOfFiles = folder.listFiles();
    String maxResponseTime = System.getProperty("maxResponseTime");
    String meanResponseTime = System.getProperty("meanResponseTime");




    @Test
    public void collectionCheckout() throws FileNotFoundException, InterruptedException {
        Thread.sleep(10000);

        String className = new Object(){}.getClass().getEnclosingMethod().getName();

        File checkoutFile = new File(listOfFiles[0] + "/js/global_stats.json");
        JSONParser parser = new JSONParser();

        String[] stats = {"numberOfRequests", "minResponseTime", "maxResponseTime", "meanResponseTime","group4"};
        System.out.println("Results for ---->" +className);
        for (int i = 0; i < stats.length; i++) {
            try {
                Object obj = parser.parse(new FileReader(checkoutFile));
                JSONObject jsonObject = (JSONObject) obj;

                Object obj1 = parser.parse(jsonObject.get(stats[i]).toString());
                JSONObject jsonObject1 = (JSONObject) obj1;


                if(i==0){
                    System.out.println("Actual Requests---->"+jsonObject1.get("total").toString() +"----------" +"Expected Requests----->"+jsonObject1.get("ok").toString());
                    Assert.assertEquals(jsonObject1.get("total").toString(),jsonObject1.get("ok").toString());
                }
                else if(i==3){
                    System.out.println("Mean Response time---->" +(Long)jsonObject1.get("total"));
                    Assert.assertTrue((Long) jsonObject1.get("total") <= Long.valueOf(meanResponseTime));
                }

                else if(i==2){
                    System.out.println("Max Response time---->" +(Long) jsonObject1.get("total"));
                    Assert.assertTrue((Long) jsonObject1.get("total") <= Long.valueOf(maxResponseTime));
                }

                else if(i==4){
                    System.out.println("Failed requests Percentile---->" +(Long) jsonObject1.get("percentage"));
                    Assert.assertTrue((Long) jsonObject1.get("percentage") == 0);
                }



            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("########################################################");
    }


    @Test
    public void testStatsForRegisteredUser() throws FileNotFoundException, InterruptedException {

        String className = new Object(){}.getClass().getEnclosingMethod().getName();

            File checkoutFile = new File(listOfFiles[1] + "/js/global_stats.json");
            JSONParser parser = new JSONParser();

            String[] stats = {"numberOfRequests", "minResponseTime", "maxResponseTime", "meanResponseTime","group4"};
        System.out.println("Results for ---->" +className);
        for (int i = 0; i < stats.length; i++) {
                try {
                    Object obj = parser.parse(new FileReader(checkoutFile));
                    JSONObject jsonObject = (JSONObject) obj;

                    Object obj1 = parser.parse(jsonObject.get(stats[i]).toString());
                    JSONObject jsonObject1 = (JSONObject) obj1;


                  if(i==0){
                      System.out.println("Actual Requests---->"+jsonObject1.get("total").toString() +"----------" +"Expected Requests----->"+jsonObject1.get("ok").toString());
                      Assert.assertEquals(jsonObject1.get("total").toString(),jsonObject1.get("ok").toString());
                  }
                    else if(i==3){
                      System.out.println("Mean Response time---->" +(Long)jsonObject1.get("total"));
                      Assert.assertTrue((Long) jsonObject1.get("total") <= Long.valueOf(meanResponseTime));
                  }

                  else if(i==2){
                      System.out.println("Max Response time---->" +(Long) jsonObject1.get("total"));
                      Assert.assertTrue((Long) jsonObject1.get("total") <= Long.valueOf(maxResponseTime));
                  }

                  else if(i==4){
                      System.out.println("Failed requests Percentile---->" +(Long) jsonObject1.get("percentage"));
                      Assert.assertTrue((Long) jsonObject1.get("percentage") == 0);
                  }



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        System.out.println("########################################################");
        }

    @Test
    public void testStatsForCheckout() throws FileNotFoundException, InterruptedException {

        String className = new Object(){}.getClass().getEnclosingMethod().getName();

        File checkoutFile = new File(listOfFiles[2] + "/js/global_stats.json");
        JSONParser parser = new JSONParser();

        String[] stats = {"numberOfRequests", "minResponseTime", "maxResponseTime", "meanResponseTime","group4"};
        System.out.println("Results for ---->" +className);
        for (int i = 0; i < stats.length; i++) {
            try {
                Object obj = parser.parse(new FileReader(checkoutFile));
                JSONObject jsonObject = (JSONObject) obj;

                Object obj1 = parser.parse(jsonObject.get(stats[i]).toString());
                JSONObject jsonObject1 = (JSONObject) obj1;


                if(i==0){
                    System.out.println("Actual Requests---->"+jsonObject1.get("total").toString() +"----------" +"Expected Requests----->"+jsonObject1.get("ok").toString());
                    Assert.assertEquals(jsonObject1.get("total").toString(),jsonObject1.get("ok").toString());
                }
                else if(i==3){
                    System.out.println("Mean Response time---->" +(Long)jsonObject1.get("total"));
                    Assert.assertTrue((Long) jsonObject1.get("total") <= Long.valueOf(meanResponseTime));
                }

                else if(i==2){
                    System.out.println("Max Response time---->" +(Long) jsonObject1.get("total"));
                    Assert.assertTrue((Long) jsonObject1.get("total") <= Long.valueOf(maxResponseTime));
                }

                else if(i==4){
                    System.out.println("Failed requests Percentile---->" +(Long) jsonObject1.get("percentage"));
                    Assert.assertTrue((Long) jsonObject1.get("percentage") == 0);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("########################################################");
    }

    @Test
    public void testStatsForMultipleCheckout() throws FileNotFoundException, InterruptedException {

        String className = new Object(){}.getClass().getEnclosingMethod().getName();
        File checkoutFile = new File(listOfFiles[3] + "/js/global_stats.json");
        JSONParser parser = new JSONParser();


        String[] stats = {"numberOfRequests", "minResponseTime", "maxResponseTime", "meanResponseTime","group4"};
        System.out.println("Results for ---->" +className);
        for (int i = 0; i < stats.length; i++) {
            try {
                Object obj = parser.parse(new FileReader(checkoutFile));
                JSONObject jsonObject = (JSONObject) obj;

                Object obj1 = parser.parse(jsonObject.get(stats[i]).toString());
                JSONObject jsonObject1 = (JSONObject) obj1;


                if(i==0){
                    System.out.println("Actual Requests---->"+jsonObject1.get("total").toString() +"----------" +"Expected Requests----->"+jsonObject1.get("ok").toString());
                    Assert.assertEquals(jsonObject1.get("total").toString(),jsonObject1.get("ok").toString());
                }
                else if(i==3){
                    System.out.println("Mean Response time---->" +(Long)jsonObject1.get("total"));
                    Assert.assertTrue((Long) jsonObject1.get("total") <= Long.valueOf(meanResponseTime));
                }

                else if(i==2){
                    System.out.println("Max Response time---->" +(Long) jsonObject1.get("total"));
                    Assert.assertTrue((Long) jsonObject1.get("total") <= Long.valueOf(maxResponseTime));
                }

                else if(i==4){
                    System.out.println("Failed requests Percentile---->" +(Long) jsonObject1.get("percentage"));
                    Assert.assertTrue((Long) jsonObject1.get("percentage") == 0);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("########################################################");
    }

    @Test
    public void testStatsForRegisterNewUser() throws FileNotFoundException, InterruptedException {

        String className = new Object(){}.getClass().getEnclosingMethod().getName();
        File checkoutFile = new File(listOfFiles[4] + "/js/global_stats.json");
        JSONParser parser = new JSONParser();

        String[] stats = {"numberOfRequests", "minResponseTime", "maxResponseTime", "meanResponseTime","group4"};
        System.out.println("Results for ---->" +className);
        for (int i = 0; i < stats.length; i++) {
            try {
                Object obj = parser.parse(new FileReader(checkoutFile));
                JSONObject jsonObject = (JSONObject) obj;

                Object obj1 = parser.parse(jsonObject.get(stats[i]).toString());
                JSONObject jsonObject1 = (JSONObject) obj1;


                if(i==0){
                    System.out.println("Actual Requests---->"+jsonObject1.get("total").toString() +"----------" +"Expected Requests----->"+jsonObject1.get("ok").toString());
                    Assert.assertEquals(jsonObject1.get("total").toString(),jsonObject1.get("ok").toString());
                }
                else if(i==3){
                    System.out.println("Mean Response time---->" +(Long)jsonObject1.get("total"));
                    Assert.assertTrue((Long) jsonObject1.get("total") <= Long.valueOf(meanResponseTime));
                }

                else if(i==2){
                    System.out.println("Max Response time---->" +(Long) jsonObject1.get("total"));
                    Assert.assertTrue((Long) jsonObject1.get("total") <= Long.valueOf(maxResponseTime));
                }

                else if(i==4){
                    System.out.println("Failed requests Percentile---->" +(Long) jsonObject1.get("percentage"));
                    Assert.assertTrue((Long) jsonObject1.get("percentage") == 0);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("########################################################");
    }

    @Test
    public void testStatsForStoreFinder() throws FileNotFoundException, InterruptedException {

        String className = new Object(){}.getClass().getEnclosingMethod().getName();
        File checkoutFile = new File(listOfFiles[5] + "/js/global_stats.json");
        JSONParser parser = new JSONParser();

        String[] stats = {"numberOfRequests", "minResponseTime", "maxResponseTime", "meanResponseTime","group4"};
        System.out.println("Results for ---->" +className);
        for (int i = 0; i < stats.length; i++) {
            try {
                Object obj = parser.parse(new FileReader(checkoutFile));
                JSONObject jsonObject = (JSONObject) obj;

                Object obj1 = parser.parse(jsonObject.get(stats[i]).toString());
                JSONObject jsonObject1 = (JSONObject) obj1;


                if(i==0){
                    System.out.println("Actual Requests---->"+jsonObject1.get("total").toString() +"----------" +"Expected Requests----->"+jsonObject1.get("ok").toString());
                    Assert.assertEquals(jsonObject1.get("total").toString(),jsonObject1.get("ok").toString());
                }
                else if(i==3){
                    System.out.println("Mean Response time---->" +(Long)jsonObject1.get("total"));
                    Assert.assertTrue((Long) jsonObject1.get("total") <= Long.valueOf(meanResponseTime));
                }

                else if(i==2){
                    System.out.println("Max Response time---->" +(Long) jsonObject1.get("total"));
                    Assert.assertTrue((Long) jsonObject1.get("total") <= Long.valueOf(maxResponseTime));
                }

                else if(i==4){
                    System.out.println("Failed requests Percentile---->" +(Long) jsonObject1.get("percentage"));
                    Assert.assertTrue((Long) jsonObject1.get("percentage") == 0);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("########################################################");
    }

    }
