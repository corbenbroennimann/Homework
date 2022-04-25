package com.android.example.usingthreads

import java.net.URL

class ImageNDescriptions() {
    val imageList = listOf<URL>(
        URL("https://www.nps.gov/yose/planyourvisit/images/20170618_155330.jpg"),
        URL("https://static.toiimg.com/photo/69200947.cms"),
        URL("https://www.myutahparks.com/wp-content/uploads/2021/02/Arches-DelicateArch-LaSalMountains_DP_1600.jpg"),
        URL("https://upload.wikimedia.org/wikipedia/commons/a/aa/Dawn_on_the_S_rim_of_the_Grand_Canyon_%288645178272%29.jpg"),
        URL("https://seasaltandfog.com/wp-content/uploads/2020/10/Sequoia_1-1024x795.jpg"),
        URL("https://travelnevada.com/wp-content/uploads/2014/04/DVNP_Featured.jpg"),
        URL("https://media.tacdn.com/media/attractions-splice-spp-674x446/06/73/d9/bb.jpg"),
        URL("https://historyfangirl.com/wp-content/uploads/2020/06/shutterstock_132426206.jpg"),
        URL("https://www.nps.gov/crla/learn/nature/images/lake-by-Kim-Chamales.jpg"),
        URL("https://static.onecms.io/wp-content/uploads/sites/28/2017/01/waterfall-HOTSPRINGS0117.jpg")
    )

    val descriptionList = listOf<String>(
        "Yosemite National Park, CA",
        "Yellow Stone National Park, WY",
        "Arches National Park, UT",
        "Grand Canyon National Park, AR",
        "Sequoia National Park, CA",
        "Death Valley National Park, CA",
        "Volcanoes National Park, HI",
        "Carlsbad Caves National Park, NM",
        "Crater Lake National Park, OR",
        "Hot Springs National Park, AR"
    )
}