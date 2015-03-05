@james
Feature: Check contents of shopping bag

  As a user of m+s website I want the shopping bag to contain only the items I put in it.

   Scenario: Add shirt to bag and view bag
        Given I have added a shirt to my bag
        When I view the contents of my bag
        Then I can see the contents of the bag include a shirt