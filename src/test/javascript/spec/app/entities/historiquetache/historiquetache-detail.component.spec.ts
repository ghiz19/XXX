/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { HistoriquetacheDetailComponent } from 'app/entities/historiquetache/historiquetache-detail.component';
import { Historiquetache } from 'app/shared/model/historiquetache.model';

describe('Component Tests', () => {
    describe('Historiquetache Management Detail Component', () => {
        let comp: HistoriquetacheDetailComponent;
        let fixture: ComponentFixture<HistoriquetacheDetailComponent>;
        const route = ({ data: of({ historiquetache: new Historiquetache(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [HistoriquetacheDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(HistoriquetacheDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HistoriquetacheDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.historiquetache).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
