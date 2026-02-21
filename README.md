Very simple tag browser application that makes use of InputChips and FilterChips to allow the user to select some animals from a list, formatted with FlowColumn/FlowRow.

Built in Android Studio for min SDK Android version 10 (API 29)

<img width="401" height="852" alt="image" src="https://github.com/user-attachments/assets/76859057-8e89-4eb5-9436-f8d1a10d6e40" />

App running on Pixel 9a (API 36) emulator

**AI disclosure:**

I used ChatGPT to help debug the random selection function that I wrote, because when I used it in the emulator environment it kept crashing and I was struggling to figure out why. The issue was pretty obvious, I accidentally set up my list indexing incorrectly and the function was causing an out of bounds error due to my use of the `..` operator. ChatGPT informed me of my problem, and advised me to use the `until` operator for the range of indexes instead to avoid going out of bounds. I fixed my code according to the suggestion, and it solved the problem with crashing.
