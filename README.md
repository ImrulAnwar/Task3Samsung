<ul>
<p>The Application follows Layered Architecture combined with MVVM design pattern to ensure seperation of concerns, maintainability and scalability.</p>
<ul>
  <li>Precise Location permission is required to function properly.</li>
   <li>
    <img src="https://github.com/user-attachments/assets/b45d807a-e480-4357-aba5-694b1eb10450" alt="App permission" style="width: 200px;">
  </li>
</ul>
<p>Assuming the location only needs to get updated when the user is running the App. I've used a combination of Flow & Coroutine to periodically update the location.</p>
<p>If the location was needed to get updated in real time, I would've used a Foreground Service. If the application does not have to remain open, I would've used a WorkManager.</p>
<li>Instead of 10 minutes its being updated in 3 seconds in the gif for demonstration. In the application your location will get updated every 10 minutes.</li>
<ul>
  <li>
    <img src="https://github.com/user-attachments/assets/3a28e266-5161-4165-b9a1-bf307d17ef8b" alt="Update Location" style="width: 200px;">
  </li>
</ul>
<p>To test out the application the apk is provided in the <a href = "https://github.com/ImrulAnwar/Task3Samsung/releases">release</a> section:</p>
