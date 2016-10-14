create or replace
TYPE      XXPNA_ITEM_DET_TYPE AS OBJECT (
      p_item_description   VARCHAR2	(240)
  ,p_item_quantity      NUMBER
  ,p_request_date       DATE
  ,p_customer_number    VARCHAR2 (10)
  ,created_by           NUMBER
  ,last_updated_by      NUMBER
  ,last_updated_date    DATE
  ,creation_date        DATE
  ,last_update_login    NUMBER
  ,p_price_list         NUMBER
  ,p_item_number        VARCHAR2	(40)
  ,p_currency           VARCHAR2	(30)
  ,p_selling_price      NUMBER
  ,p_available           NUMBER
  ,p_available_date     DATE
  ,p_uom               VARCHAR2	(25)
  ,p_atp_flag           VARCHAR2 (1)
  ,p_discount           VARCHAR2	(240)
  ,p_dis_desc          VARCHAR2	(2000)
  ,p_dis_valid_date     DATE
  ,p_warehouse          VARCHAR2 (100)
  ,attribute1           VARCHAR2 (150 BYTE)
  ,attribute2           VARCHAR2 (150 BYTE)
  ,attribute3           VARCHAR2 (150 BYTE)
  ,attribute4           VARCHAR2 (150 BYTE)
  ,attribute5           VARCHAR2 (150 BYTE)
  ,attribute6           VARCHAR2 (150 BYTE)
  ,attribute7           VARCHAR2 (150 BYTE)
  ,attribute8           VARCHAR2 (150 BYTE)
  ,attribute9           VARCHAR2 (150 BYTE)
  ,attribute10          VARCHAR2 (150 BYTE)
  ,attribute11          VARCHAR2 (150 BYTE)
  ,attribute12          VARCHAR2 (150 BYTE)
  ,attribute13          VARCHAR2 (150 BYTE)
  ,attribute14          VARCHAR2 (150 BYTE)
  ,attribute15          VARCHAR2 (150 BYTE)
); 