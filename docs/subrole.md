# Sub Role API Specifications

## Create Sub Role

Endpoint: POST /api/roles/{idRole}/subroles

Request Body:
```json
{
  "subrolename": "Administrator"
}
```

Response Body (Success):
```json
{
  "data": {
    "id": "auto incremented number",
    "subrolename": "Administrator"  
  }
}
```

Response Body (Failed):
```json
{
  "errors": "Sub role is already exist"
}
```

## Read Sub Role

Endpoint: GET /api/roles/{idRole}/subroles/{idSubRole}

Response Body (Success):
```json
{
  "data": {
    "id": "auto incremented number",
    "subrolename": "Administrator"
  }
}
```

Response Body (Failed, 404):
```json
{
  "errors": "Sub role is not found"
}
```

## Update Role

Endpoint: PATCH /api/roles/{idRole}/subroles/{idSubRole}

Request Body:
```json
{
  "subrolename": "Administrator"
}
```

Response Body (Success):
```json
{
  "data": {
    "id": "auto incremented number",
    "subrolename": "Administrator"
  }
}
```

Response Body (Failed):
```json
{
  "errors": "Sub role is already exist"
}
```

## Delete Sub Role
Endpoint: DELETE /api/roles/{idRole}/subroles/{idSubRole}

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