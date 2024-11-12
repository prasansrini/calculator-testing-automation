import unittest
from appium import webdriver
from appium.options.android import UiAutomator2Options
from appium.webdriver.common.appiumby import AppiumBy

capabilities = dict(
    platformName='Android',
    automationName='uiautomator2',
    deviceName='Android',
    appPackage='com.sec.android.app.popupcalculator',
	appActivity='com.sec.android.app.popupcalculator.Calculator',
    language='en',
    locale='US'
)

# appium_server_url = 'http://10.20.38.240:4723'
appium_server_url = 'http://127.0.0.1:4723'

class TestAppium(unittest.TestCase):
    def setUp(self) -> None:
        self.driver = webdriver.Remote(appium_server_url, options=UiAutomator2Options().load_capabilities(capabilities))

    def tearDown(self) -> None:
        if self.driver:
            self.driver.quit()

    # Find element by resource ID.
    def test_calculator_check_using_id(self) -> None:
        el1 = self.driver.find_element(by=AppiumBy.ACCESSIBILITY_ID, value="7")
        el1.click()
        el2 = self.driver.find_element(by=AppiumBy.ACCESSIBILITY_ID, value="Plus")
        el2.click()
        el3 = self.driver.find_element(by=AppiumBy.ACCESSIBILITY_ID, value="6")
        el3.click()
        el4 = self.driver.find_element(by=AppiumBy.ACCESSIBILITY_ID, value="Calculation")
        el4.click()

        # Find element by resource ID.
        result_value = self.driver.find_element(by=AppiumBy.ID, value="com.sec.android.app.popupcalculator:id/calc_edt_formula").text
        assert result_value[:2] == '13'

    # Find element by XPATH.
    def test_calculator_check_using_xpath(self) -> None:
        el1 = self.driver.find_element(by=AppiumBy.ACCESSIBILITY_ID, value="7")
        el1.click()
        el2 = self.driver.find_element(by=AppiumBy.ACCESSIBILITY_ID, value="Plus")
        el2.click()
        el3 = self.driver.find_element(by=AppiumBy.ACCESSIBILITY_ID, value="6")
        el3.click()
        el4 = self.driver.find_element(by=AppiumBy.ACCESSIBILITY_ID, value="Calculation")
        el4.click()
        
        result_value = self.driver.find_element(by=AppiumBy.XPATH, value='//android.widget.EditText[@resource-id="com.sec.android.app.popupcalculator:id/calc_edt_formula"]').text
        assert result_value[:2] == '13'


if __name__ == '__main__':
    unittest.main()