async function viewSelectableCharacter() {
    const groupId = window.localStorage.getItem("groupId");

    const selectedImages = await fetch(`/api/groups/${groupId}/profile-image`)
        .then(response => response.json())
        .then(data => data.selectedImages);

    console.log(selectedImages);

    selectedImages.forEach(image => {
        const profileLocation = image.profileLocation
        const index = profileLocation.indexOf("pring");
        const name = profileLocation.substr(index, 6);
        console.log(name);
        const pring = document.getElementById(name);

        console.log(pring);

        pring.removeAttribute("href");
        pring.children[0].src = `/images/group/character/gray-${name}.svg`;
    });
}

viewSelectableCharacter();
