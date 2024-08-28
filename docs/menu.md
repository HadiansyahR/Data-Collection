# Menu API Specifications

## Create Menu

Endpoint: POST /api/menus
 
Request Body:
```json
{
  "menuname": "Dashboard",
  "labelmenu": "Dashboard Menu"
}
```

Response Body (Success):
```json
{
  "data": {
    "id": "auto incremented number",
    "menuname": "Dashboard",
    "labelmenu": "Dashboard Menu"  
  }
}
```

Response Body (Failed):
```json
{
  "errors": "Name is already taken"
}
```

## Read Menu

Endpoint: GET /api/menus/{idMenu}

Response Body (Success):
```json
{
  "data": {
    "id": "auto incremented number",
    "menuname": "Dashboard",
    "labelmenu": "Dashboard Menu"  
  }
}
```

Response Body (Failed, 404):
```json
{
  "errors": "Menu is not found"
}
```

## Update Menu

Endpoint: PATCH /api/menus/{idMenu}

Request Body:
```json
{
  "menuname": "Dashboard new",
  "labelmenu": "Dashboard Menu new"
}
```

Response Body (Success):
```json
{
  "data": {
    "id": "auto incremented number",
    "menuname": "Dashboard new",
    "labelmenu": "Dashboard Menu new"
  }
}
```

Response Body (Failed):
```json
{
  "errors": "Name is already taken"
}
```

## Delete Menu
Endpoint: DELETE /api/menus/{idMenu}

Response Body (Success):
```json
{
  "data": "OK"
}
```

Response Body (Failed, 404):
```json
{
  "errors": "Menu is not found"
}
```