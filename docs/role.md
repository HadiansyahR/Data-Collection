# Role API Specifications

## Create Role

Endpoint: POST /api/roles

Request Body:
```json
{
  "rolename": "Web User",
  "rolestatus": "Super Admin"
}
```

Response Body (Success):
```json
{
  "data": {
    "id": "auto incremented number",
    "rolename": "Web User",
    "rolestatus": "Super Admin"  
  }
}
```

Response Body (Failed):
```json
{
  "errors": "Role is already exist"
}
```

## Read Role

Endpoint: GET /api/roles/{idRole}

Response Body (Success):
```json
{
  "data": {
    "id": "auto incremented number",
    "rolename": "Web User",
    "rolestatus": "Super Admin" 
  }
}
```

Response Body (Failed, 404):
```json
{
  "errors": "Role is not found"
}
```

## Update Role

Endpoint: PUT /api/roles/{idRole}

Request Body:
```json
{
  "rolename": "Web User",
  "rolestatus": "Super Admin"
}
```

Response Body (Success):
```json
{
  "data": {
    "id": "auto incremented number",
    "rolename": "Web User",
    "rolestatus": "Super Admin"
  }
}
```

Response Body (Failed):
```json
{
  "errors": "Role is already exist"
}
```

## Delete Role
Endpoint: DELETE /api/roles/{idRole}

Response Body (Success):
```json
{
  "data": "OK"
}
```

Response Body (Failed, 404):
```json
{
  "errors": "Role is not found"
}
```