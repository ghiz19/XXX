/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { PlanprevetinfDetailComponent } from 'app/entities/planprevetinf/planprevetinf-detail.component';
import { Planprevetinf } from 'app/shared/model/planprevetinf.model';

describe('Component Tests', () => {
    describe('Planprevetinf Management Detail Component', () => {
        let comp: PlanprevetinfDetailComponent;
        let fixture: ComponentFixture<PlanprevetinfDetailComponent>;
        const route = ({ data: of({ planprevetinf: new Planprevetinf(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [PlanprevetinfDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlanprevetinfDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlanprevetinfDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.planprevetinf).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
