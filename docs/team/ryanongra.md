---
layout: page
title: Ryan Ong's Project Portfolio Page
---

### Project: For Your Interest

ForYourInterest - ForYourInterest is a desktop app for managing members in university clubs/societies, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **Improved Feature**: Modified and improved the `add` command.
  * Improvement: Allows the user to omit certain details about the added person and only need to provide the name (minimally). Also changed the address detail of the person to telegram handle.
  * Justification: This feature significantly improve the practical usability of the application since sometimes the user does not have all the details about a member. This improvement allows the to add a member with unspecified details, then update the details at a later time (using the `edit` command). Address was changed to telegram handle to better suit the needs of our target user who will have a greater use for a member's telegram handle, than for their home address.
  * Highlights: In addition to changing the feature itself, the implementation of this feature required changes to the storage component. Although it was not the main component I was in charge of, I was able to quickly pick up and understand how it worked to make the necessary changes. I also needed to modify existing test cases to ensure that they were applicable to the features.  

* **New Feature**: Added event modification features (`renameEvent` command and `removePersonFromEvent` command).
  * What it does: Allows the user to modify an existing event by changing its name and removing people form the event.
  * Justification: This is a necessary supporting feature for the event feature. Without it, users would have to manually delete the entire event and recreate it from scratch by adding in the correct member, risking data loss due to human error.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=ryanongra&tabRepo=AY2122S1-CS2103-T16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Held Zoom meetings for the team's weekly meetings. 

* **Enhancements to existing features**:
    * Wrote additional tests for new and existing features (Commits [AddCommandParserTest](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/81/commits/084af90435f05edc07f74adbc9214091ccfae329), [JsonAdaptedPersonTest](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/81/commits/6a7fb1c107668a4a3eb7e33d47361eeb8ef5acdc))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `add`, `details`, `renameEvent`, and `removePersonFromEvent` [\#20](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/20) [\#87](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/87/commits/e98a927bf98fb4889ce9d100376c812d6c282235) [\#138](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/138) 
        * Added screenshots and videos for app demos: [Project Management Document](https://docs.google.com/document/d/1-H421cvAuV-pI8mNIWEZZ4qh4tuU-aGIbmptDFOgGuk/edit)
    * Developer Guide:
        * Added implementation details of the `add`, `renameEvent`, and `removePersonFromEvent` features and the `Person` class [\#78](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/78)
        * Modified the class diagram for the `Person` class [PersonClassDiagram](https://github.com/AY2122S1-CS2103-T16-4/tp/blob/master/docs/images/PersonClassDiagram.png)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#57](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/57), [\#75](https://github.com/AY2122S1-CS2103-T16-4/tp/pull/75)
