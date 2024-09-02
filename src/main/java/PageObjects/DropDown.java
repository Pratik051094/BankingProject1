package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropDown {

	private WebDriver driver;

	public DropDown(WebDriver driver) {
		this.driver = driver;
	}

	public void DrpDnAccess(String arialabel, String dataVal) {
		ScrollContainer("//button[@aria-label='" + arialabel + "']");
		if (isElementClickable(driver, By.xpath("//button[@aria-label='" + arialabel + "']"), Duration.ofSeconds(20))) {
			driver.findElement(By.xpath("//button[@aria-label='" + arialabel + "']")).click();
			WebElement we = driver.findElement(By.xpath("//div[@aria-label='" + arialabel + "']"));
			we.findElement(By.xpath("//*[@data-value='" + dataVal + "']")).click();
		}

	}

	public void ScrollContainer(String xpath) {
		WebElement element = driver.findElement(By.xpath("(//section)[2]"));
		WebElement elm = element.findElement(By.xpath(xpath));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", elm);
		waitForElementToClickeable(driver, By.xpath(xpath), Duration.ofSeconds(20));
	}
	
	public void ScrollContainerX(String xpath) {
		WebElement element = driver.findElement(By.xpath("(//section)[2]"));
		WebElement elm = element.findElement(By.xpath(xpath));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", elm);
	}

	public void ScrollParent(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static WebElement waitForElementToAppear(WebDriver driver, By locator, Duration i) {
		WebDriverWait wait = new WebDriverWait(driver, i);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static Boolean waitForElementToDisAppear(WebDriver driver, By locator, Duration i) {
		WebDriverWait wait = new WebDriverWait(driver, i);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public static WebElement waitForElementToClickeable(WebDriver driver, By locator, Duration i) {
		WebDriverWait wait = new WebDriverWait(driver, i);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static boolean isElementClickable(WebDriver driver, By locator, Duration timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementInvisible(WebDriver driver, By locator, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            // Wait for the element to be invisible and return the result
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)) != null;
        } catch (Exception e) {
            // Log the exception if needed
            return false;
        }
    }
	
	public boolean emptyElm(String xpath) {
		try {
			ScrollContainerX(xpath);
			if(driver.findElement(By.xpath(xpath)).getAttribute("aria-invalid")!=null) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			return false;
		}
	}
	
//	public boolean elmtrueWait() {
//		if(emptyElm("(//input[@class='slds-input'])[5]")==false && emptyElm("(//input[@class='slds-input'])[6]")==false && emptyElm("(//input[@class='slds-input'])[7]")==false &&
//        		emptyElm("(//input[@class='slds-input'])[12]")==false && emptyElm("(//input[@class='slds-input'])[13]")==false && emptyElm("(//input[@class='slds-input'])[14]")==false &&
//        		emptyElm("(//input[@class='slds-input'])[19]")==false && emptyElm("(//input[@class='slds-input'])[20]")==false && emptyElm("(//input[@class='slds-input'])[21]")==false &&
//        		emptyElm("(//input[@class='slds-input'])[26]")==false && emptyElm("(//input[@class='slds-input'])[27]")==false && emptyElm("(//input[@class='slds-input'])[28]")==false ) 
//		{
//			WebElement element = driver.findElement(By.xpath("(//input[@class='slds-input'])[28]"));
//	        String value = element.getAttribute("aria-invalid");
//	        return value == null || value.trim().isEmpty();
//		}
//        
//	}
	public static boolean waitForAriaInvalidAttribute(WebDriver driver, final By locator, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        WebElement element = driver.findElement(locator);
                        String ariaInvalid = element.getAttribute("aria-invalid");
                        return ariaInvalid != null && !ariaInvalid.trim().isEmpty();
                    } catch (Exception e) {
                        // Handle or log the exception as needed
                        return false;
                    }
                }
            });
        } catch (Exception e) {
            // Optionally log the exception if needed
            return false;
        }
    }
	public static boolean waitForRequired(WebDriver driver, final By locator, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        WebElement element = driver.findElement(locator);
                        String ariaInvalid = element.getAttribute("max");
                        return ariaInvalid != null && !ariaInvalid.trim().isEmpty();
                    } catch (Exception e) {
                        // Handle or log the exception as needed
                        return false;
                    }
                }
            });
        } catch (Exception e) {
            // Optionally log the exception if needed
            return false;
        }
    }
}
