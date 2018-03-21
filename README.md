# PAMDemo

This project generates a PAM survey and stores the results in a PAM.csv file in the **/Documents** directory on your Android device.

The PAM survey is triggered by calling: `RSActivityManager.get().queueActivity(this, "RSpam", true);` as shown in line 22 in **MainActivity.java** in PAMDemo.

In order to add a PAM survey to your own project:

1. First set up a ResearchStack Survey App as shown [here](https://github.com/fnokeke/NewBeehiveSurvey/blob/master/README.md): 
2. Download and copy this [pam.json](https://github.com/christinatsan/PAMDemo/blob/master/app/src/main/assets/json/pam.json) file into your **assets/json** folder
3. Download and copy this [RSpam.json](https://github.com/christinatsan/PAMDemo/blob/master/app/src/main/assets/json/RSpam.json) file into your **assets/json** folder
4. Go to **META-INF/services** and edit the **edu.cornell.tech.foundry.researchsuitetaskbuilder.RSTBStepGeneratorServiceProvider.spi.RSTBStepGenerator** file to add the following line: `edu.cornell.tech.foundry.sdl_rsx_rstbsupport.PAMStepGenerator`
5. To display the survey make sure your calling activity extends `RSActivity`, like **MainActivity.java** in PAMDemo. You can then use `RSActivityManager.get().queueActivity(this, "RSpam", true);` to trigger PAM. 

In order to save the PAM survey results in a CSV file in your own project:

1. Download this PAMDemo. 
2. Go to your project and select **File** -> **New** -> **Import Module**. Click to select the source directory and navigate to your PAMDemo download location & select the **rsrp** folder.
3. Go to **build.gradle (Module: app)** and add the following under dependencies: `compile project(':rsrp')`
4. Download and copy this [RSRPBackendSupport] (https://github.com/christinatsan/PAMDemo/tree/master/app/src/main/java/org/researchsuite/pamdemo/RSRPBackendSupport) folder from PAMDemo into your project (in the same hierarchy as the studyManagement folder). You can delete the files not related to PAM in this folder.
5. Download and copy this [studyManagement](https://github.com/christinatsan/PAMDemo/tree/master/app/src/main/java/org/researchsuite/pamdemo/studyManagement) folder from PAMDemo into your project. You must overwrite your existing studyManagement folder from the original ResearchStack Survey App template in order to support the new CSV Backend module.
6. Go to **META-INF/services** and add a new file. Name it **org.researchsuite.rsrp.Core.RSRPFrontEndServiceProvider.spi.RSRPFrontEnd**. Edit it to add the following line: `org.researchsuite.pamdemo.RSRPBackendSupport.PAMCSVTransformer`. Be careful - the file name should be [your path to RSRPBackendSupport folder].PAMCSVTransformer, so in my case it's org.researchsuite.pamdemo. 
7. Go to **RSRPBackendSupport** folder and edit the `supportsType()` function in **PAMCSVTransformer.java** to make sure that the path here to `RSRPBackendSupport.PAMCSVEncodable.TYPE` is also your path to that file instead of mine (like in step. 6!)           
   ````java
   @Override
    public boolean supportsType(String type) {
        if (type.equals(org.researchsuite.pamdemo.RSRPBackendSupport.PAMCSVEncodable.TYPE)) return true;
        else return false;
    }
    
8. Now your PAM survey result will be saved to a PAM.csv file in the Documents directory on your device. Each time a PAM survey is completed the result is appended to this file. This file is automatically created if it doesn't exist by the RSRP module, so you can export + delete it and a new empty file will be created.

In general, you can save the results of ResearchStack step by extending RSRPIntermediateResult (as shown in **PAMRaw.java** in **RSRPBackendSupport** in the PAMDemo), creating a custom CSVTransformer (as shown in **PAMCSVTransformer.java**) and a CSVEncodable (as shown in **PAMCSVEncodable.java**). 
The CSVEncodable should return:
1. The header for you csv file (the 1st row in your csv)
2. The records corresponding to each header value for the following rows in your csv
3. The type of your survey - which should be `return this.getTaskIdentifier()` always

The file saved will always have the name of the Step Identifier you specify in your json file.
You will also need to add a **resultTransforms** item in your json file as shown in **RSpam.json** (https://github.com/christinatsan/PAMDemo/blob/master/app/src/main/assets/json/RSpam.json) where the value for `transform` needs to be your custom encodable filename & the value for `stepIdentifier` needs to be the step identifier of your survey step specified above in your json.
