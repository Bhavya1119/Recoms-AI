

document.addEventListener("DOMContentLoaded", () => {

  fetch('/api/v1/auth/status')
    .then(response => response.json())
    .then(data => {
      console.log("data ", data)
      const authButton = document.getElementById('authButton');
      const isLoggedIn = data.loggedIn;

      if (isLoggedIn) {
        authButton.textContent = "Logout";
        authButton.addEventListener('click', () => {
          console.log("inside logout click method ..... ")
          window.location.href = '/api/v1/signout';
        });
      } else {
        authButton.textContent = "Login";
        authButton.addEventListener('click', () => {
          window.location.href = '/api/v1/signin';
        });
      }

      // Attach event listeners to tabs
      document.getElementById('albumsTab').addEventListener('click', () => {
        if (isLoggedIn) {
          window.location.href = '/album.html';
        } else {
          alert('You must be logged in to view albums');
        }
      });

      document.getElementById('topArtistsTab').addEventListener('click', () => {
        if (isLoggedIn) {
          window.location.href = '/topArtists.html';
        } else {
          alert('You must be logged in to view top artists');
        }
      });
    })
    .catch(error => {
      console.error('Error checking auth status:', error);
    });
});
