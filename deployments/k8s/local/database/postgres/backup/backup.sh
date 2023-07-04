#!/bin/bash

# PostgreSQL connection details
HOST="$POSTGRES_HOST"
PORT="$POSTGRES_PORT"
DATABASE="$POSTGRES_DB"
USERNAME="$POSTGRES_USER"
PASSWORD="$POSTGRES_PASSWORD"

# Backup directory and file name
BACKUP_DIR="/backup"
DATE=$(date +"%Y%m%d%H%M%S")
BACKUP_FILE="$BACKUP_DIR/db_backup_$DATE.sql"

# Create the backup
pg_dump -h "$HOST" -p "$PORT" -U "$USERNAME" -W "$PASSWORD" -F p -b -v -f "$BACKUP_FILE" "$DATABASE"

# Check the backup status
if [ $? -eq 0 ]; then
  echo "Backup created successfully: $BACKUP_FILE"
else
  echo "Backup creation failed!"
fi
