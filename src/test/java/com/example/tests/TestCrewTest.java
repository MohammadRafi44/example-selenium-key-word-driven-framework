package com.example.tests;

import com.example.base.UiBaseTest;
import com.example.modules.AmazonModule;
import com.example.modules.TestCrewModule;
import com.example.utils.DataReader;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestCrewTest extends UiBaseTest {

    @Test(description = "TestCrew Site Test")
    @Parameters(value = "tcId")
    public void TestCrewAboutUsTextVerify(String tcId) {
        TestCrewModule testCrewModule = new TestCrewModule();
        testCrewModule.launchTestCrewWebSite(DataReader.getData(tcId).get("Url"));
        testCrewModule.AboutUsPage(tcId);
    }

    @Test(description = "Test Advisory & Consulting Services - Page Verification")
    @Parameters(value = "tcId")
    public void ServiceMenu_TestAdivsory_Page_Check(String tcId) {
        TestCrewModule testCrewModule = new TestCrewModule();
        testCrewModule.launchTestCrewWebSite(DataReader.getData(tcId).get("Url"));
        testCrewModule.Test_Advisory_Verify(tcId);
    }

}
