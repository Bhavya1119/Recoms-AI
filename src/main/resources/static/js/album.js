
/**
* Created by Bhavya Joshi
*/

 //function to convert millisecond duration in mm:ss format
 function formatDuration(ms) {
        const minutes = Math.floor(ms / 60000);
        const seconds = ((ms % 60000) / 1000).toFixed(0);
        return `${minutes}:${seconds.padStart(2, '0')}`;
    }

//function for formatting child of artists json
    function formatArtistInfo(artists) {
        return artists.map(artist => `
            <div class="artist-info">
                <p><strong>Name:</strong> ${artist.name}</p>
                <p><strong>ID:</strong> ${artist.id}</p>
                <p><strong>URL:</strong> <a class="artist-url" href="${artist.url}" target="_blank">${artist.url}</a></p>
            </div>
        `).join('');
    }

// frontend function to display the data from the api in presentable manner
    function fetchAlbumDetails() {
        const albumId = document.getElementById('albumId').value;
        const albumDetails = document.getElementById('albumDetails');
        const errorMessage = document.getElementById('errorMessage');

        if (!albumId) {
            errorMessage.style.display = 'block';
            errorMessage.textContent = 'Please enter an Album ID';
            return;
        }

        //hit the custom /getAlbumDetails API
        fetch(`/getAlbumDetails/${albumId}`)
            .then(response => response.json())
            .then(data => {
                // Create table structure
                let tableHTML = `
                    <table>
                        <thead>
                            <tr>
                                <th>Track ID</th>
                                <th>Title</th>
                                <th>Duration</th>
                                <th>Artists</th>
                            </tr>
                        </thead>
                        <tbody>
                `;

                // Add each track as a row
                data.forEach(track => {
                    tableHTML += `
                        <tr>
                            <td>${track.trackID}</td>
                            <td>${track.title}</td>
                            <td class="duration">${formatDuration(track.duration)}</td>
                            <td>${formatArtistInfo(track.artists)}</td>
                        </tr>
                    `;
                });

                tableHTML += `
                        </tbody>
                    </table>
                `;

                albumDetails.innerHTML = tableHTML;
                errorMessage.style.display = 'none';
            })
            .catch(error => {
                errorMessage.style.display = 'block';
                errorMessage.textContent = 'Error fetching album details';
                console.error('Error:', error);
            });
    }