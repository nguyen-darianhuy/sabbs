use databois;
create trigger prevent_listing_dupes before insert on Listings for each row
  BEGIN
    if (exists(select 1 from Listings
                        where new.Address = Address and
                              new.Capacity = Capacity and
                              new.Price = Price and
                              new.Region = Region)) then
      SIGNAL SQLSTATE 'LD001'
      SET MESSAGE_TEXT = 'Possible duplicate entry detected, either remove or deactivate the listing before updating';
    end if;
  end;

create trigger prevent_invalid_booking before insert on Transactions for each row
  BEGIN
    if (exists(select 1 from Transactions where lid = new.lid
                                                and not(NOT (startDate > new.endDate OR
                                                             endDate < new.startDate)))) then
      SIGNAL SQLSTATE 'TD001'
      SET MESSAGE_TEXT = 'Cant book';
    end if;
  end;
