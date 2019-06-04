/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { HistoriquetacheService } from 'app/entities/historiquetache/historiquetache.service';
import { IHistoriquetache, Historiquetache } from 'app/shared/model/historiquetache.model';

describe('Service Tests', () => {
    describe('Historiquetache Service', () => {
        let injector: TestBed;
        let service: HistoriquetacheService;
        let httpMock: HttpTestingController;
        let elemDefault: IHistoriquetache;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(HistoriquetacheService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Historiquetache(0, 'AAAAAAA', currentDate, currentDate);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        datetimedebut: currentDate.format(DATE_TIME_FORMAT),
                        detetimedebut: currentDate.format(DATE_TIME_FORMAT)
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

            it('should create a Historiquetache', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        datetimedebut: currentDate.format(DATE_TIME_FORMAT),
                        detetimedebut: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datetimedebut: currentDate,
                        detetimedebut: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Historiquetache(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Historiquetache', async () => {
                const returnedFromService = Object.assign(
                    {
                        description: 'BBBBBB',
                        datetimedebut: currentDate.format(DATE_TIME_FORMAT),
                        detetimedebut: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        datetimedebut: currentDate,
                        detetimedebut: currentDate
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

            it('should return a list of Historiquetache', async () => {
                const returnedFromService = Object.assign(
                    {
                        description: 'BBBBBB',
                        datetimedebut: currentDate.format(DATE_TIME_FORMAT),
                        detetimedebut: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datetimedebut: currentDate,
                        detetimedebut: currentDate
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

            it('should delete a Historiquetache', async () => {
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
