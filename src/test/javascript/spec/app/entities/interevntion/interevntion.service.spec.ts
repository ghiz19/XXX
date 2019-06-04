/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { InterevntionService } from 'app/entities/interevntion/interevntion.service';
import { IInterevntion, Interevntion } from 'app/shared/model/interevntion.model';

describe('Service Tests', () => {
    describe('Interevntion Service', () => {
        let injector: TestBed;
        let service: InterevntionService;
        let httpMock: HttpTestingController;
        let elemDefault: IInterevntion;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(InterevntionService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Interevntion(0, currentDate, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        datedebutintervention: currentDate.format(DATE_TIME_FORMAT),
                        datefinintervention: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Interevntion', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        datedebutintervention: currentDate.format(DATE_TIME_FORMAT),
                        datefinintervention: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datedebutintervention: currentDate,
                        datefinintervention: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Interevntion(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Interevntion', async () => {
                const returnedFromService = Object.assign(
                    {
                        datedebutintervention: currentDate.format(DATE_TIME_FORMAT),
                        datefinintervention: currentDate.format(DATE_TIME_FORMAT),
                        duree: 'BBBBBB',
                        coutmaindeuvre: 'BBBBBB',
                        coutinterevntion: 'BBBBBB',
                        solutions: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        datedebutintervention: currentDate,
                        datefinintervention: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Interevntion', async () => {
                const returnedFromService = Object.assign(
                    {
                        datedebutintervention: currentDate.format(DATE_TIME_FORMAT),
                        datefinintervention: currentDate.format(DATE_TIME_FORMAT),
                        duree: 'BBBBBB',
                        coutmaindeuvre: 'BBBBBB',
                        coutinterevntion: 'BBBBBB',
                        solutions: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datedebutintervention: currentDate,
                        datefinintervention: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Interevntion', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
