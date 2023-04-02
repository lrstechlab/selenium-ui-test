@tag
Feature: to Fetch all the job list
  I want to use this template for my feature file


  Background:
    Given  I landed on tipico homePage

  @Regression
  Scenario: Positive Test of Submitting the order

    Given I navigated to the JobsPage
    When i fetch all the job
    Then I store all the fetched job details in mySQLDB



