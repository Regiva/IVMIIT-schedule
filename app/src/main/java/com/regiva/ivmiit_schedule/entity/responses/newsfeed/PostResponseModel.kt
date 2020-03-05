package com.regiva.ivmiit_schedule.entity.responses.newsfeed

data class PostResponseModel(
    val id: Long,
    val from_id: Long,
    val owner_id: Long,
    val date: Long,
    val text: String?,
    val attachments: List<Attachment>?
)

//todo

/*{
  "response": {
    "count": 1067,
    "items": [
      {
        "id": 126730,
        "from_id": -86529522,
        "owner_id": -86529522,
        "date": 1489599074,
        "marked_as_ads": 0,
        "post_type": "post",
        "text": "Зона «Экстрим» на VK Fest 2017",
        "can_pin": 1,
        "attachments": [
          {
            "type": "photo",
            "photo": {
              "id": 456239461,
              "album_id": -7,
              "owner_id": -86529522,
              "user_id": 100,
              "photo_75": "https://pp.userap...5a3/_LwF-Hsv4B4.jpg",
              "photo_130": "https://pp.userap...5a4/k8a0uXCbkMY.jpg",
              "photo_604": "https://pp.userap...5a5/TKjIcburlwc.jpg",
              "photo_807": "https://pp.userap...5a6/tdN8S-RaCQw.jpg",
              "photo_1280": "https://pp.userap...5a7/E0vOE2dnkNY.jpg",
              "width": 1280,
              "height": 853,
              "text": "",
              "date": 1489591865,
              "access_key": "c543a7268173be3a73"
            }
          }
        ],
        "post_source": {
          "type": "vk"
        },
        "comments": {
          "count": 25,
          "can_post": 1
        },
        "likes": {
          "count": 202,
          "user_likes": 0,
          "can_like": 1,
          "can_publish": 1
        },
        "reposts": {
          "count": 11,
          "user_reposted": 0
        },
        "views": {
          "count": 27807
        }
      }
    ],
    "profiles": [],
    "groups": [
      {
        "id": 86529522,
        "name": "VK Fest",
        "screen_name": "fest",
        "is_closed": 0,
        "type": "event",
        "is_admin": 0,
        "is_member": 0,
        "photo_50": "https://pp.userap...6d0/VogboWaykYA.jpg",
        "photo_100": "https://pp.userap...6cf/B7yTl3PtpoE.jpg",
        "photo_200": "https://pp.userap...6ce/k4CKjWl2znY.jpg"
      }
    ]
  }
}*/