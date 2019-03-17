create trigger prevent_listing_dupes before insert on Listings for each row
  BEGIN
    if (exists(select 1 from Listings
                        where new.Address = Address and
                              new.Capacity = Capacity and
                              new.Price = Price and
                              new.Region = Region and
                              new.cusid = cusid)) then
      SIGNAL SQLSTATE 'LD001'
      SET MESSAGE_TEXT = 'Possible duplicate entry detected, either remove or deactivate the listing before updating';
    end if;
  end;

create trigger prevent_invalid_booking before insert on TRANSACTION for each row_count
  BEGIN
    if (exsists(select 1 from Listings
                              ))
    end if;
  end;

# view avaliable listings
set @StartDate = STR_TO_DATE('02,02,2019','%d,%m,%Y');
set @EndDate = STR_TO_DATE('03,02,2019','%d,%m,%Y');
select * from Listings lsts
              inner join
              (select distinct t.lid from Transactions t
                                          where not
                                                  @StartDate #Start Date
                                                    > t.endDate and
                                                  @EndDate
                                                    < t.startDate)
                as avaliable_listings
                on avaliable_listings.lid = lsts.id;

## view currently active listings
select * from Listings lsts
              inner join (select distinct lid from Transactions
              where current_date > startDate and current_date < endDate) as currently_booked
              on currently_booked.lid = lsts.id;

