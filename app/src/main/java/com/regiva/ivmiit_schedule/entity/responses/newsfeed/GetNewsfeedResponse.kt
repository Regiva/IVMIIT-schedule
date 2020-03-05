package com.regiva.ivmiit_schedule.entity.responses.newsfeed

data class GetNewsfeedResponse(
    val items: List<PostResponseModel>,
    val profiles: List<ProfileModel>,
    val groups: List<GroupModel>,
    val next_from: String? //todo
)

data class ProfileModel(
    val id: Long,
    val first_name: String,
    val last_name: String,
    val photo_50: String,
    val photo_100: String
) {
    fun getName() = "$first_name $last_name"
}

data class GroupModel(
    val id: Long,
    val name: String,
    val photo_50: String,
    val photo_100: String,
    val photo_200: String
)

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
          },
          {
            "type": "photo",
            "photo": {
              "id": 456239462,
              "album_id": -7,
              "owner_id": -86529522,
              "user_id": 100,
              "photo_75": "https://pp.userap...5ac/u789f_w1UJk.jpg",
              "photo_130": "https://pp.userap...5ad/utm799ZCvXc.jpg",
              "photo_604": "https://pp.userap...5ae/AoapurQDFuE.jpg",
              "photo_807": "https://pp.userap...5af/hK4sX29uUy8.jpg",
              "photo_1280": "https://pp.userap...5b0/LRgSg0yLo_0.jpg",
              "width": 1280,
              "height": 853,
              "text": "",
              "date": 1489591865,
              "access_key": "ec42afbf3816dd3649"
            }
          },
          {
            "type": "photo",
            "photo": {
              "id": 456239463,
              "album_id": -7,
              "owner_id": -86529522,
              "user_id": 100,
              "photo_75": "https://pp.userap...5b5/r-3kUS5aFrw.jpg",
              "photo_130": "https://pp.userap...5b6/-7WUE8Qlei4.jpg",
              "photo_604": "https://pp.userap...5b7/IUKjzd7_Aak.jpg",
              "photo_807": "https://pp.userap...5b8/neNWbaxUIFs.jpg",
              "photo_1280": "https://pp.userap...5b9/101iNsGb8kU.jpg",
              "width": 1280,
              "height": 853,
              "text": "",
              "date": 1489591865,
              "access_key": "b6974be82887bbc2c3"
            }
          },
          {
            "type": "photo",
            "photo": {
              "id": 456239464,
              "album_id": -7,
              "owner_id": -86529522,
              "user_id": 100,
              "photo_75": "https://pp.userap...5be/3Ay6NZzrUQU.jpg",
              "photo_130": "https://pp.userap...5bf/AKG60YTMwIo.jpg",
              "photo_604": "https://pp.userap...5c0/XuuOWgs2BhU.jpg",
              "photo_807": "https://pp.userap...5c1/LfdOtFqFBnM.jpg",
              "photo_1280": "https://pp.userap...5c2/bWuztHdPCn8.jpg",
              "width": 1280,
              "height": 853,
              "text": "",
              "date": 1489591865,
              "access_key": "a89744c0f15ae3d8e6"
            }
          },
          {
            "type": "photo",
            "photo": {
              "id": 456239465,
              "album_id": -7,
              "owner_id": -86529522,
              "user_id": 100,
              "photo_75": "https://pp.userap...5d0/NCLqk-67jxE.jpg",
              "photo_130": "https://pp.userap...5d1/9jRAyzx4Mw8.jpg",
              "photo_604": "https://pp.userap...5d2/e37VhQDHphE.jpg",
              "photo_807": "https://pp.userap...5d3/wNvyxYvc-Ds.jpg",
              "photo_1280": "https://pp.userap...5d4/dDP6BnlQVDQ.jpg",
              "width": 1280,
              "height": 852,
              "text": "",
              "date": 1489591865,
              "access_key": "9293cb0f268c0e557f"
            }
          },
          {
            "type": "photo",
            "photo": {
              "id": 456239466,
              "album_id": -7,
              "owner_id": -86529522,
              "user_id": 100,
              "photo_75": "https://pp.userap...5c7/DQJkuUfKYzI.jpg",
              "photo_130": "https://pp.userap...5c8/OVffMtvngB8.jpg",
              "photo_604": "https://pp.userap...5c9/URhp-nxiz9g.jpg",
              "photo_807": "https://pp.userap...5ca/CqC6xqvfpGg.jpg",
              "photo_1280": "https://pp.userap...5cb/fOg0ScQrXLY.jpg",
              "width": 1280,
              "height": 853,
              "text": "",
              "date": 1489591865,
              "access_key": "71a93e8c0f89fe8941"
            }
          },
          {
            "type": "photo",
            "photo": {
              "id": 456239467,
              "album_id": -7,
              "owner_id": -86529522,
              "user_id": 100,
              "photo_75": "https://pp.userap...5d9/Kw56t7UJ1Vg.jpg",
              "photo_130": "https://pp.userap...5da/y86g7YF93-A.jpg",
              "photo_604": "https://pp.userap...5db/vcRPUbZXTEg.jpg",
              "photo_807": "https://pp.userap...5dc/H5DHB-OB3-k.jpg",
              "photo_1280": "https://pp.userap...5dd/YX1k-Tf8zoo.jpg",
              "width": 1280,
              "height": 852,
              "text": "",
              "date": 1489591865,
              "access_key": "5c6ca2c75fa7ace449"
            }
          },
          {
            "type": "photo",
            "photo": {
              "id": 456239468,
              "album_id": -7,
              "owner_id": -86529522,
              "user_id": 100,
              "photo_75": "https://pp.userap...5e2/tQf8RFO4s-E.jpg",
              "photo_130": "https://pp.userap...5e3/AmrFW5cE8nQ.jpg",
              "photo_604": "https://pp.userap...5e4/iORmli0Dm0A.jpg",
              "photo_807": "https://pp.userap...5e5/XW8uBTa1ZQU.jpg",
              "photo_1280": "https://pp.userap...5e6/vwG5n363uE8.jpg",
              "width": 1280,
              "height": 852,
              "text": "",
              "date": 1489591865,
              "access_key": "83bb01dd9c1e2b68c4"
            }
          },
          {
            "type": "photo",
            "photo": {
              "id": 456239469,
              "album_id": -7,
              "owner_id": -86529522,
              "user_id": 100,
              "photo_75": "https://pp.userap...5eb/700v6vmPhvg.jpg",
              "photo_130": "https://pp.userap...5ec/AMukYRrsqgE.jpg",
              "photo_604": "https://pp.userap...5ed/NTZf5AT_bs8.jpg",
              "photo_807": "https://pp.userap...5ee/rv1GxbRDh9I.jpg",
              "photo_1280": "https://pp.userap...5ef/TwcktRgXIbc.jpg",
              "width": 1280,
              "height": 855,
              "text": "",
              "date": 1489591865,
              "access_key": "290e8eeedeca3cbb78"
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